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
    public void firstMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("hello \n1.create user \n2.login \n3.exit");
        System.out.print("enter your choice:");
        int choice=scanner.nextInt();
        if (choice==1){
            createUser();
            firstMenu();
        }
        else if (choice==2){
            User user=login();
            if ( user != null){
                secondMenu(user);
            }
            else {
                firstMenu();
            }

        }
        else {
            System.out.println("goodbye");
        }

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
            }
            case 2:{
                removeProperty(user);
                secondMenu(user);
            }
            case 3: {
                printAllProperties();
                secondMenu(user);
            }
            case 4: {
                printAllProperties(user);
                secondMenu(user);
            }
            case 5:{

            }
            case 6:{
                firstMenu();
            }
        }

    }
    public boolean isUsernameExist (String usernameToCheck) {
        boolean exists = false;
        for (int i = 0; i < this.userArray.length; i++) {
            User currentUser = this.userArray[i];
            if (currentUser.getUserName().equals(usernameToCheck)) {
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
            if (currentUser.getUserName().equals(username)) {
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
    public void createUser()
    {
        Scanner scanner = new Scanner(System.in);
        String username = null;
        String password = null;
        String phone = null;
        int type;
        boolean regular=false;
        do {
            System.out.println("Enter username: ");
            username = scanner.next();
        } while (isUsernameExist(username));

        do {
            System.out.println("Enter password: ");
            password = scanner.next();
        } while (!isStrongPassword(password));

        do {
            System.out.println("Enter your phone: ");
            phone = scanner.next();
        } while (!isPhoneNumberGood(phone));

        do {
            System.out.println("Enter your type:" +
                    "\nEnter 1 for regular" +
                    "\nEnter 2 for mediator): ");
            type = scanner.nextInt();
        } while (type!=1  && type!=2);
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
        System.out.println("Enter username: ");
        username = scanner.next();
        System.out.println("Enter password: ");
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
        if (user.getType()==false){
            if (amount<3){
                postProperty=createProperty(user);
            }
            else {
                postProperty=false;
            }
        }
        else {
            if (amount<10) {
                postProperty = createProperty(user);
            }
        }
        return postProperty;
    }
    public int amountOfProperty(User user){
        int counter=0;
        for (int i=0;i<properties.length;i++){
            if (user.getUserName().equals(properties[i].getUser())){
                counter++;
            }
        }
        return counter;
    }
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
        System.out.println("\nenter the chosen city:");
        String city = scanner.next();
        if (isExistCity(city)) {
            postProperty = true;
            int num = numOfStreets(city);
            showStreets(city);
            System.out.println("\nenter the chosen street:");
            street = scanner.next();
            if (isExistStreet(street, city)) {
                postProperty = true;
                System.out.println("\nenter the type of the property (1)regular apartment \n2)penthouse \n3)private house");
                type = scanner.nextInt();
                if (type == 1) {
                    System.out.println("in which floor that apartment is?");
                    floor = scanner.nextInt();
                }
                System.out.println("how much rooms?");
                rooms = scanner.nextDouble();
                System.out.println("what the number of the property?");
                numOfHouse = scanner.nextInt();
                System.out.println("what the state of the property?(rent or sell");
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
                type1="regular apartment";
            }
            else if(type==2){
                type1="penthouse";
            }
            else if (type==3){
                type1="private house";
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
    public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int amount =amountOfProperty(user);
        int indexOfProperty=-1;
        if(amount>0) {
            Property[] property = allMyProperties(user);
            Property[] newPropertiesArray=new Property[this.properties.length-1];
            int propertyToRemove = scanner.nextInt();
            for (int i=0;i<this.properties.length;i++){
                if(this.properties[i].equals(property[propertyToRemove])){
                    indexOfProperty=i;
                    break;
                }
            }
            int i=0,j=0;
            while (i<this.properties.length){
                if (i!=indexOfProperty) {
                    newPropertiesArray[j] = this.properties[i];
                    i++;
                    j++;
                }
                else {
                    i++;
                }
            }
            this.properties=newPropertiesArray;
            System.out.println("the post is removed...");
        }
        else {
            System.out.println("you don't post any property\ngoodbey" );
        }
    }
    public Property[] allMyProperties(User user){
        int amount =amountOfProperty(user);
        Property[] properties1=new Property[amount];
        int j=0;
        for (int i=0;i<this.properties.length;i++){
            if(this.properties[i].getUser().equals(user.getUserName())){
                properties1[j]=this.properties[i];
                j++;
            }
        }

        return properties1;
    }
    public void printAllProperties(){
        for (int i=0;i<this.properties.length;i++){
            System.out.println((i+1) +"." + this.properties[i]);
        }
    }
    public void printAllProperties(User user){
        Property[] properties1=allMyProperties(user);
        for (int k=0;k<properties1.length;k++){
            System.out.println((k+1) + "."  + properties1[k]);
        }
    }
}
