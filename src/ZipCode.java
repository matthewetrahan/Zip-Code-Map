import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

// display zip code locations
// file format is:
// Zipcode  ZipCodeType City    State   LocationType    Lat Long    Location    Decommisioned   TaxReturnsFiled EstimatedPopulation TotalWages
// lattitude is the 6th field and longitude is the 7th field

public class ZipCode {

    public static final String DATA_FILE = "C:\\Users\\Laptop\\Downloads\\zipCode_48.csv";

    public static final int WIDTH = 900;
    public static final int HEIGHT = 500;

    public static void main(String[] args ) {
        Scanner sc = createScanner();

        double minLat = 90;
        double maxLat = -90;
        double minLong = 180;
        double maxLong = -180;

        while(sc.hasNextLine()) {
            skipTokens(sc);
            double lat = sc.nextDouble();
            double longi = sc.nextDouble();
            // System.out.println(lat + ", " + longi);
            sc.nextLine();
            minLat = Math.min(minLat, lat);
            maxLat = Math.max(maxLat, lat);
            minLong = Math.min(minLong, longi);
            maxLong = Math.max(maxLong, longi);
        }
        sc.close();
        System.out.println(minLat + " " + maxLat + " " + minLong + " " + maxLong);
        drawZips(minLat, maxLat, minLong, maxLong);
    }

    public static Scanner createScanner() {
        try {
            Scanner sc = new Scanner(new File(DATA_FILE));
            // skip the first line
            sc.nextLine();
            sc.useDelimiter(",");
            return sc;
        }
        catch(IOException e) {
            System.out.println("Unable to create scanner. Program likely to crash.");
            return null;
        }
    }

    // skip the first 5 tokesn in the line
    public static void skipTokens(Scanner sc) {
        final int TOKENS_TO_SKIP = 5;
        for(int i = 0; i < TOKENS_TO_SKIP; i++) {
            sc.next();
        }
    }

    public static void drawZips(double minLat, double maxLat, 
            double minLong, double maxLong)  {

        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        dp.setBackground(Color.BLACK);
        Graphics g = dp.getGraphics();
        g.setColor(Color.WHITE);
        
        double pixelsPerDegreeLat = HEIGHT / (maxLat - minLat);
        double pixelsPerDegreeLong = WIDTH / (maxLong - minLong);
        Scanner sc = createScanner();
        while(sc.hasNext()) {
            skipTokens(sc);
            double lat = sc.nextDouble();
            double longi = sc.nextDouble();
            sc.nextLine();
            int x = (int) ((longi - minLong) * pixelsPerDegreeLong);
            int y = (int) ((lat - minLat) * pixelsPerDegreeLat);
            y = HEIGHT - y;
            g.fillRect(x, y, 1, 1);
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//             double pixelsPerDegreeLat = HEIGHT / (maxLat - minLat);
//         double pixelsPerDegreeLong = WIDTH / (maxLong - minLong);
//         Scanner sc = createScanner();
//         DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
//         dp.setBackground(Color.BLACK);
//         Graphics g = dp.getGraphics();
//         g.setColor(Color.WHITE);
//         while(sc.hasNextLine()) {
//             skipTokens(sc);
//             double lat = sc.nextDouble();
//             double longi = sc.nextDouble();
//             sc.nextLine();
//             int x = (int) ((longi - minLong) * pixelsPerDegreeLong);
//             int y = (int) ((lat - minLat) * pixelsPerDegreeLat);
//             // flip the y
//             y = HEIGHT - y;
//             g.fillRect(x, y, 1, 1);
//         }
//         sc.close();
}