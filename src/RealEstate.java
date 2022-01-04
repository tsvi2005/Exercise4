import java.util.Arrays;
import java.util.Scanner;

public class RealEstate {
    private User[] userArray;
    private Property[] properties;
    private Address[] addresses1;


    public RealEstate(){
        userArray=new User[0];
        properties=new Property[0];
        addresses1 = new Address[10];
        addresses1[0]=new Address("tel-aviv","herzel");
        addresses1[1]=new Address("jerusalem","rehavia");
        addresses1[2]=new Address("ashkelon","barnea");
        addresses1[3]=new Address("tel-aviv","rotshild");
        addresses1[4]=new Address("ashkelon","neot");
        addresses1[5]=new Address("jerusalem","jaffo");
        addresses1[6]=new Address("tel-aviv","King-gorge");
        addresses1[7]=new Address("ashkelon","Afridar");
        addresses1[8]=new Address("jerusalem","kiryat-yovel");
        addresses1[9]=new Address("tel-aviv","shenkin");

    }
    @Override
    public String toString() {
        return "RealEstate{" +
                "userArray=" + Arrays.toString(userArray) +
                ", properties=" + Arrays.toString(properties) +
                ", addresses=" + Arrays.toString(addresses1) +
                '}';
    }
    public void secondMenu(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.post new property \n2.remove exist property \n3.show all properties \n4.show my properties \n5.search property \n6.logout and back first menu");
        System.out.print("enter your choice:");
        int choice=scanner.nextInt();
        switch (choice){
            case 1: {
                boolean result=postNewProperty(user);
                System.out.println(result);
                secondMenu(user);
                break;
            }
            case 2:{
                removeProperty(user);
                secondMenu(user);
                break;
            }
            case 3: {
                printAllProperties();
                secondMenu(user);
                break;
            }
            case 4: {
                printAllProperties(user);
                secondMenu(user);
                break;
            }
            case 5:{
                Property [] property = search();
                secondMenu(user);
                break;
            }
            case 6:{
                Main.firstMenu();
                break;
            }
        }

    }
    public boolean isUsernameExist (String usernameToCheck) {
        boolean exists = false;
        for (int i = 0; i < this.userArray.length; i++) {
            User currentUser = this.userArray[i];
            if (currentUser.getUsername().equals(usernameToCheck)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
    public int indexOfTheUsername(String username){
        int index=-1;
        for (int i = 0; i < this.userArray.length; i++) {
            User currentUser = this.userArray[i];
            if (currentUser.getUsername().equals(username)) {
                index=i;
                break;
            }
        }
        return index;
    }
    public boolean isPasswordCorrect(String username ,String password){
        boolean passwordCorrect=false;
        if (isUsernameExist(username)){
            int index = indexOfTheUsername(username);
            if(userArray[index].getPassword().equals(password)){
                passwordCorrect=true;
            }
        }
        return passwordCorrect;
    }
    public void addUserToArray (String username, String password, String phone, boolean type) {
        User[] newArray = new User[this.userArray.length + 1];
        for (int i = 0; i < this.userArray.length; i++) {
            newArray[i] = this.userArray[i];
        }
        User userToAdd = new User(username, password,phone,type);
        newArray[this.userArray.length] = userToAdd;
        this.userArray = newArray;
    }
    public boolean isStrongPassword (String passwordToCheck) {
        boolean strong = false;
        boolean hasDigit = false;
        boolean hasSpecialChar  =false;
        for (int i = 0; i < passwordToCheck.length(); i++) {
            char currentChar = passwordToCheck.charAt(i);
            if (Character.isDigit(currentChar)) {
                hasDigit = true;
            }
            if (currentChar=='%' || currentChar=='_' || currentChar=='$') {
                hasSpecialChar = true;
            }
            if (hasSpecialChar && hasDigit) {
                strong = true;
                break;
            }
        }
        return strong;
    }
    public boolean isPhoneNumberGood(String phone) {
        boolean proper = false;
        if (phone.length() < 10 || phone.length() > 10) {
            proper = false;
        } else {
            if (phone.charAt(0) == '0' && phone.charAt(1) == '5') {
                proper = true;
                for (int i=2;i<phone.length();i++) {
                    char current=phone.charAt(i);
                    if (Character.isDigit(current)) {
                        proper = true;
                    } else {
                        proper = false;
                        break;
                    }
                }
            } else {
                proper = false;
            }
        }
        return proper;
    }
    public final String ENTER_YOUR = "Enter your ";
    public final String USERNAME = "username: ";
    public final String PASSWORD = "password: ";
    public final String PHONE_NUM = "phone number: ";
    public final String TYPE = "type(1.regular_user,2.real_estate_broker): ";
    public final String CITY = "chosen city:";
    public final String STREET = "chosen street: ";


    public void createUser()
    {
        Scanner scanner = new Scanner(System.in);
        String username = null;
        String password = null;
        String phone = null;
        int type;
        boolean regular=false;
        do {
            System.out.println(ENTER_YOUR+USERNAME);
            username = scanner.next();
        } while (isUsernameExist(username));

        do {
            System.out.println(ENTER_YOUR+PASSWORD);
            password = scanner.next();
        } while (!isStrongPassword(password));

        do {
            System.out.println(ENTER_YOUR+PHONE_NUM);
            phone = scanner.next();
        } while (!isPhoneNumberGood(phone));

        do {
            System.out.println(ENTER_YOUR+TYPE);
            type = scanner.nextInt();
        } while (type!=(1)  && type!=(2));
        if (type==1){
            regular=true;
        }
        addUserToArray(username, password ,phone , regular);
    }
    public User login(){
        Scanner scanner = new Scanner(System.in);
        User user=null;
        boolean details = false;
        String username = null;
        String password = null;
        System.out.println(ENTER_YOUR+USERNAME);
        username = scanner.next();
        System.out.println(ENTER_YOUR+PASSWORD);
        password = scanner.next();
        if (isUsernameExist(username)){
            if (isPasswordCorrect(username,password)){
                details=true;
            }
        }
        if (details){
            user=userArray[indexOfTheUsername(username)];
        }else {
            System.out.println("there isn't user like this...");
        }
        return user;
    }
    public boolean postNewProperty(User user){
        boolean postProperty=false;
        int amount=amountOfProperty(user);
        if (user.getRegular()){
            if (amount<3){
                postProperty=createProperty(user);
            }
            else {
                postProperty=false;
            }
        }
        else
        {
            if (amount<10) {
                postProperty = createProperty(user);
            }
        }
        return postProperty;
    }
    public final String REGULAR_APARTMENT="regular apartment";
    public final String PENTHOUSE="penthouse";
    public final String PRIVATE_HOUSE ="private house";
    public final String RENT_SALE ="Is the property for rent or for sale?\n"+
                              " Press 1 for rent\n"  +
                               "Press 2 for sale";
    public final String TYPE_OF_PROPERTY = "Enter the type of the property\n"+
            "Press 1 for "+REGULAR_APARTMENT+"\n"+ "Press 2 for "+PENTHOUSE+"\n"+"Press 3 for "+PRIVATE_HOUSE;
    public final String ROOM="How much rooms?";
    public boolean createProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean postProperty=false;
        String street ="";
        int type=0;
        String type1="";
        int floor=-1;
        double rooms=0;
        int numOfHouse=0;
        int price=0;
        String state="";
        showOptionalCities();
        System.out.println("\n"+ENTER_YOUR+CITY);
        String city = scanner.next();
        if (isExistCity(city)) {
            postProperty = true;
            int num = numOfStreets(city);
            showStreets(city);
            System.out.println("\n"+ENTER_YOUR+STREET);
            street = scanner.next();
            if (isExistStreet(street, city)) {
                postProperty = true;
                System.out.println(TYPE_OF_PROPERTY);
                type = scanner.nextInt();
                if (type == 1) {
                    System.out.println("in which floor that apartment is?");
                    floor = scanner.nextInt();
                }
                System.out.println(ROOM);
                rooms = scanner.nextDouble();
                System.out.println("what the number of the property?");
                numOfHouse = scanner.nextInt();
                System.out.println(RENT_SALE);
                state = scanner.next();
                System.out.println("what the price of the property?");
                price = scanner.nextInt();
            } else {
                System.out.println("there isn't street like this");
                postProperty = false;
            }
        }
        else {
            System.out.println("there isn't city like this");
            postProperty = false;
        }
        Address address=new Address(city,street);
        if (postProperty){
            if (type==1){
                type1=REGULAR_APARTMENT;
            }
            else if(type==2){
                type1=PENTHOUSE;
            }
            else if (type==3){
                type1=PRIVATE_HOUSE;
            }
            Property property=new Property(address,type1,floor,rooms,numOfHouse,state,price,user);
            addPropertyToArray(property);
        }

        return postProperty;
    }
    public void addPropertyToArray(Property property){
        Property[] newArray = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            newArray[i] = this.properties[i];
        }
        Property propertyToAdd = property;
        newArray[this.properties.length] = propertyToAdd;
        this.properties = newArray;
    }

    public void showOptionalCities(){
        Address [] address = removeDuplicates();
        for (int i=0;i<address.length;i++){
            System.out.print(address[i].getCity() + ",");
        }
    }
    public  Address[] removeDuplicates()
    {
        Address [] address = new Address[this.addresses1.length];
        for (int a=0;a<this.addresses1.length;a++){
            address[a]=this.addresses1[a];
        }
        int end = address.length;
        int shiftLeft=0;
        for (int i = 0; i < end; i++)
        {
            for (int j = i + 1; j < end; j++)
            {
                if (address[i].getCity() == address[j].getCity()) {
                    shiftLeft = j;
                    for (int k = j+1; k < end; k++, shiftLeft++) {
                        address[shiftLeft] = address[k];
                    }
                    end--;
                    j--;
                }
            }
        }

        Address[] whitelist = new Address[shiftLeft];
        for(int i = 0; i < shiftLeft; i++){
            whitelist[i] = address[i];
        }
        return whitelist;
    }
    public boolean isExistCity(String city){
        boolean check=false;
        for (int i = 0; i<this.addresses1.length; i++){
            if (this.addresses1[i].getCity().equals(city)){
                check=true;
                break;
            }
        }
        return check;
    }
    public void showStreets(String city){
        for (int i = 0; i<this.addresses1.length; i++) {
            if (this.addresses1[i].getCity().equals(city)) {
                System.out.print(this.addresses1[i].getStreet() + ",");
            }
        }
    }
    public int numOfStreets(String city){
        int counter=0;
        for (int i = 0; i<this.addresses1.length; i++){
            if (this.addresses1[i].getCity().equals(city)){
                counter++;
            }
        }
        return counter;
    }
    public Address[] streetsByCity(String city){
        int amount=numOfStreets(city);
        Address [] addresses=new Address[amount];
        int j=0;
        for (int i = 0; i<this.addresses1.length; i++) {
            if (this.addresses1[i].getCity().equals(city)) {
                addresses[j] = this.addresses1[i];
                j++;
            }
        }
        return addresses;
    }
    public boolean isExistStreet(String street,String city){
        boolean check=false;
        Address [] address = streetsByCity(city);
        for (int i=0;i<address.length;i++){
            if (address[i].getStreet().equals(street)){
                check=true;
                break;
            }
        }
        return check;
    }
    public int amountOfProperty(User user){
        int counter=0;
        for (int i=0;i<this.properties.length;i++){
            if (properties[i].getUser().getUsername().equals(user.getUsername())){
                counter++;
            }
        }
        return counter;
    }
    public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int amount =amountOfProperty(user);
        int indexOfProperty=-1;
        int propertyToRemove=0;
        if(amount>0) {
            Property[] property = allMyProperties(user);
            printAllProperties(user);
            System.out.println("choose which property do you want to remove:");
            propertyToRemove = scanner.nextInt();
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].equals(property[propertyToRemove-1])) {
                    indexOfProperty = i;
                    break;
                }
            }
            if (this.properties.length==1) {
                Property[] newPropertiesArray=new Property[this.properties.length-1];
                this.properties[this.properties.length-1]=null;
                this.properties=newPropertiesArray;
                System.out.println("that property removed!");
            }
            else {
                Property[] newPropertiesArray=new Property[this.properties.length-1];
                this.properties[indexOfProperty] = this.properties[this.properties.length - 1];
                this.properties[this.properties.length - 1] = null;
                for (int i = 0; i < this.properties.length-1; i++) {
                    newPropertiesArray[i] = this.properties[i];
                }
                this.properties = newPropertiesArray;
                System.out.println("that property removed!");
            }
        }
        else {
            System.out.println("you don't post any property\ngoodbey" );
        }
    }
    public Property[] allMyProperties(User user){
        int amount = amountOfProperty(user);
        Property[] properties1=new Property[amount];
        int j=0;
        for (int i=0;i<this.properties.length;i++){
            if(this.properties[i].getUser().getUsername().equals(user.getUsername())){
                properties1[j]=this.properties[i];
                j++;
            }
        }

        return properties1;
    }
    public void printAllProperties(){
        if (this.properties.length>=1) {
            for (int i = 0; i < this.properties.length; i++) {
                System.out.println((i + 1) + "." + this.properties[i]);
            }
        }
        else {
            System.out.println("there isn't properties that post");
        }
    }
    public void printAllProperties(User user){
        Property[] properties1=allMyProperties(user);
        if (properties1.length==0){
            System.out.println("you don't post any properties");
        }
        else {
            for (int k = 0; k < properties1.length; k++) {
                System.out.println((k + 1) + "." + properties1[k]);
            }
        }
    }
    public final int MIN_PRICE = 3;
    public final int MAX_PRICE =4;
    public  Property[] search(){
        Property[] suitableProperties = new Property[properties.length];
        for (int i = 0; i < suitableProperties.length; i++) {
            suitableProperties[i] = properties[i];
        }
        int[] filterSearch = {rentOrSale(), propertyType(),roomNumber(),0,0};
        int[] priceRange = priceRanger();
        filterSearch[MIN_PRICE] = priceRange[0];
        filterSearch[MAX_PRICE]=priceRange[1];
        for (int i = 0; i < filterSearch.length; i++) {
            suitableProperties= afterFilter(suitableProperties,filterSearch[i],i);
        }
        return suitableProperties;
    }

    public final int RENT_OR_SALE = 0;
    public final int PROPERTY_TYPE =1;
    public final int ROOM_NUMBER =2;
    public Property[] afterFilter(Property[] propertyToFilter,int value , int index){
        Property[] afterFilter = new Property[propertyToFilter.length];
        int indexArr = 0;
        if (value != NO_MATTER) {
            for (int i = 0; i < propertyToFilter.length; i++) {
                if (index == RENT_OR_SALE) {
                    if (value == FOR_RENT) {
                        if (propertyToFilter[i].getState().equals("rent")){
                            afterFilter[indexArr]=propertyToFilter[i];
                            indexArr++;
                        }
                    } else {
                        if (propertyToFilter[i].getState().equals("sale")){
                            afterFilter[indexArr]=propertyToFilter[i];
                            indexArr++;
                        }
                    }
                }
                if (index == PROPERTY_TYPE) {
                    if (value==NORMAL_APARTMENT){
                        if (propertyToFilter[i].getType().equals(REGULAR_APARTMENT)){
                            afterFilter[indexArr]=propertyToFilter[i];
                            indexArr++;
                        }
                    }else if (value==NUM_PENTHOUSE){
                        if (propertyToFilter[i].getType().equals(PENTHOUSE)){
                            afterFilter[indexArr]=propertyToFilter[i];
                            indexArr++;
                        }
                    }else {
                        if (propertyToFilter[i].getType().equals(PRIVATE_HOUSE)){
                            afterFilter[indexArr]=propertyToFilter[i];
                            indexArr++;
                        }
                    }
                }
                if (index == ROOM_NUMBER) {
                    if (propertyToFilter[i].getRooms()==value){
                        afterFilter[indexArr]=propertyToFilter[i];
                        indexArr++;
                    }
                }
                if (index==MIN_PRICE){
                    if (propertyToFilter[i].getPrice()>value){
                        afterFilter[indexArr]=propertyToFilter[i];
                        indexArr++;
                    }
                }
                if (index==MAX_PRICE){
                    if (propertyToFilter[i].getPrice()<value){
                        afterFilter[indexArr]=propertyToFilter[i];
                        indexArr++;
                    }
                }
            }
            afterFilter = withoutNull(afterFilter,indexArr);
            return afterFilter;
        }else {
            return propertyToFilter;
        }
    }
    public Property[] withoutNull(Property[] afterFilter , int arrySize){
        Property[] withoutNull = new Property[arrySize];
        for (int i = 0; i < arrySize; i++) {
            withoutNull[i]=afterFilter[i];
        }
        return withoutNull;
    }
    public final String DONT_MATTER ="Press -999 if it's doesn't matter";
    public final int NO_MATTER = -999;
    public final int FOR_RENT = 1;
    public final int FOR_SALE = 2;
    public int rentOrSale(){
        int rentOrSale;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(RENT_SALE+DONT_MATTER);
            rentOrSale = scanner.nextInt();
        }while (rentOrSale!=FOR_RENT && rentOrSale!=FOR_SALE && rentOrSale!=NO_MATTER);
        return rentOrSale;
    }
    public final int NORMAL_APARTMENT= 1;
    public final int NUM_PENTHOUSE = 2;
    public final int NUM_PRIVATE_HOUSE = 3;
    public int propertyType(){
        int propertyType;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(TYPE_OF_PROPERTY);
            propertyType = scanner.nextInt();
        }while (propertyType!=NORMAL_APARTMENT&&propertyType!=NUM_PENTHOUSE&&propertyType!=NUM_PRIVATE_HOUSE&&propertyType!=NO_MATTER);
        return propertyType;
    }
    public int roomNumber(){
        int roomNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.println(ROOM);
        roomNumber = scanner.nextInt();
        return roomNumber;
    }
    public int[] priceRanger(){
        int minNum;
        int maxNum;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the price range?\n" +
                "Enter your min price range.");
        minNum = scanner.nextInt();
        System.out.println("Enter your max range price");
        maxNum = scanner.nextInt();
        int[] priceRanger = {minNum,maxNum};
        return priceRanger;
    }

}




