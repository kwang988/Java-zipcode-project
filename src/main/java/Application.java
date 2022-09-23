import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.dto.ZipCodeDTO;
import com.kenzie.app.format.AddressFormatter;
import com.kenzie.app.http.HttpUtil;

import java.util.Scanner;

public class Application {
    // URL: https://api.zippopotam.us/us/mo/saint%20louis
    public static void main(String[] args) {
        try {
            //declare variables
            String BASE_URL = "https://api.zippopotam.us/us/";
            Scanner scanner = new Scanner(System.in);
            String recipientName;
            String streetAddress;
            String city;
            String state;
            String zipCode;

            //read in user input-scanner
            System.out.println("Enter recipient name: ");
            recipientName = scanner.nextLine();
            System.out.println("Enter street address: ");
            streetAddress = scanner.nextLine();
            System.out.println("Enter city: ");
            city = scanner.nextLine();
            System.out.println("Enter state: ");
            state = scanner.nextLine();

            //replace space in city-Los Angeles
            String tempCity = city.replaceAll(" ", "%20");

            //format URL with user city and state
            String finalURL = BASE_URL + state + "/" + tempCity;
            System.out.println(finalURL);

            //call GET
            String httpResponse = HttpUtil.makeGETRequest(finalURL);
            System.out.println(httpResponse);

            //if return string contains 404, don't object map
            if (httpResponse.contains("GET request failed")) {
                System.out.printf("No zip code found");
                zipCode = "";
            } else {
                //ObjectMapper to retrieve zip code
                //1. instantiate objectMapper
                //2. declare DTO
                //3. set DTO to objectMapper.readValue()
                ObjectMapper objectMapper = new ObjectMapper();
                ZipCodeDTO zipCodeDTOObj;
                zipCodeDTOObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);
                zipCodeDTOObj.getPlaces().get(0);
                //if one zipCode returned
                if (zipCodeDTOObj.getPlaces().size() == 1) {
                    zipCode = zipCodeDTOObj.getPlaces().get(0).getPostCode();
                }
                //else loop and display all zipcodes
                else if (zipCodeDTOObj.getPlaces().size() > 1) {
                    for (int i = 0; i < zipCodeDTOObj.getPlaces().size(); i++) {
                        System.out.println(i + ")" + zipCodeDTOObj.getPlaces().get(i).getPostCode());
                    }
                }
                //prompt user to select zipcode
                System.out.println("Choose a zipCode: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                //set zipcode based on selection
                zipCode = zipCodeDTOObj.getPlaces().get(choice).getPostCode();
            }

            //print out final address
            System.out.println(AddressFormatter.formatAddress(recipientName));
            System.out.println(AddressFormatter.formatStreetAddress(streetAddress));
            System.out.println(AddressFormatter.formatAddress(city + ", " + state + " " + zipCode));
        }
        catch(Exception e){
            System.out.println("Unexpected exception: " + e);
        }
    }
    public static void main_backup(String[] args) {
        try {
            //declare variables
            String URL = "https://api.zippopotam.us/us/mo/saint%20louis";
            String httpResponse;
            //connect to API and get zipCode
            httpResponse = HttpUtil.makeGETRequest(URL);
            System.out.println(httpResponse);
            //objectMapper
            //1. instantiate objectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            //2. declare DTO object
            ZipCodeDTO zipCodeDTOObj;
            //3. read data-readValue()
            zipCodeDTOObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);
            //1. hardcode for the first element to get it work first, print out city(place name), zipcode (post code) and state
//            System.out.println("City:" + zipCodeDTOObj.getPlaces().get(0).getPlace_name());
//            System.out.println("Zipcode:" + zipCodeDTOObj.getPlaces().get(0).getPostCode());
//            System.out.println("State:" + zipCodeDTOObj.getState());
            //2. consider different scenario
            //<if there is only one item (list size is 1), set zipCode to that one>
            if (zipCodeDTOObj.getPlaces().size() == 1){
                System.out.println("Only one zip code: " + zipCodeDTOObj.getPlaces().get(0).getPostCode());
            }
            //<if more than one item (list size is greater than 1)
            else if (zipCodeDTOObj.getPlaces().size() > 1) {
                for (int i = 0; i < zipCodeDTOObj.getPlaces().size(); i++){
                    System.out.println("Zone " + i);
                    System.out.println("City:" + zipCodeDTOObj.getPlaces().get(i).getPlace_name());
                    System.out.println("Zipcode:" + zipCodeDTOObj.getPlaces().get(i).getPostCode());
                    System.out.println("State:" + zipCodeDTOObj.getState());
                }
            }
            //STATIC call the formatter from main
            String testStr = "123 Main St.";
            AddressFormatter.initCodeTable();
            System.out.println(AddressFormatter.replaceAbbreviation(testStr));
        }

        catch(Exception e){
            System.out.println("Unexpected exception: " + e);
        }

    }
}
