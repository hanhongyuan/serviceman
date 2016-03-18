package com.dstvdm.imageindex;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by paul on 2016/03/16.
 */
@Component
public class MetadataExtractor {

    private Metadata metadata;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public MetadataExtractor() {
    }

    public MetadataExtractor(File file) {
        this.file = file;
    }

    public HashMap<String, Collection> extractAllMetadata() {
        try {
            metadata = ImageMetadataReader.readMetadata(file);
            HashMap<String, Collection> list = new HashMap<String, Collection>();
            for (Directory directory : metadata.getDirectories()) {

                list.put("Tags", directory.getTags());

                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.println("ERROR: " + error);
                    }
                }
            }
            return list;

        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<PhotoLocation> extractGeoMetadata(File path) {

        final String[] acceptedExtensions = new String[]{".jpg", ".jpeg"};

        final File[] files = path.listFiles(new FileFilter() {
            public boolean accept(final File file) {
                if (file.isDirectory())
                    return false;
                for (String extension : acceptedExtensions) {
                    if (file.getName().toLowerCase().endsWith(extension))
                        return true;
                }
                return false;
            }
        });

        Collection<PhotoLocation> photoLocations = new ArrayList<PhotoLocation>();

        for (File file : files) {
            try {
                // Read all metadata from the image
                Metadata metadata = ImageMetadataReader.readMetadata(file);
                // See whether it has GPS data
                Collection<GpsDirectory> gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);
                if (gpsDirectories == null)
                    continue;
                for (GpsDirectory gpsDirectory : gpsDirectories) {
                    // Try to read out the location, making sure it's non-zero
                    GeoLocation geoLocation = gpsDirectory.getGeoLocation();
                    if (geoLocation != null && !geoLocation.isZero()) {
                        // Add to our collection for use below
                        photoLocations.add(new PhotoLocation(geoLocation, file));
                        break;
                    }
                }
            } catch (ImageProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return photoLocations;

    }

    /**
     * Simple tuple type, which pairs an image file with its {@link GeoLocation}.
     */
    public static class PhotoLocation {
        public final GeoLocation location;
        public final File file;

        public PhotoLocation(final GeoLocation location, final File file) {
            this.location = location;
            this.file = file;
        }
    }

    private static String writeHtml(Iterable<PhotoLocation> photoLocations) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
        sb.append("<style>html,body{height:100%;margin:0;padding:0;}#map_canvas{height:100%;}</style>");
        sb.append("<script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?sensor=false\"></script>");
        sb.append("<script type=\"text/javascript\">");
        sb.append("function initialise() {");
        sb.append("    var options = { zoom:2, mapTypeId:google.maps.MapTypeId.ROADMAP, center:new google.maps.LatLng(0.0, 0.0)};");
        sb.append("    var map = new google.maps.Map(document.getElementById('map_canvas'), options);");
        sb.append("    var marker;");

        for (PhotoLocation photoLocation : photoLocations) {
            final String fullPath = photoLocation.file.getAbsoluteFile().toString().trim().replace("\\", "\\\\");

            sb.append("    marker = new google.maps.Marker({");
            sb.append("        position:new google.maps.LatLng(" + photoLocation.location + "),");
            sb.append("        map:map,");
            sb.append("        title:\"" + fullPath + "\"});");
            sb.append("    google.maps.event.addListener(marker, 'click', function() { document.location = \"" + fullPath + "\"; });");
        }

        sb.append("}");
        sb.append("</script>");
        sb.append("</head>");
        sb.append("<body onload=\"initialise()\">");
        sb.append("<div id=\"map_canvas\"></div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

}
