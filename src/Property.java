public class Property {
    private Address address;
    private double rooms;
    private int price;
    private String type;
    private String state;
    private int numberOfHouse;
    private int floor;
    private User user;



    public Property(Address address,String type,int floor,double rooms,int numberOfHouse,String state,int price,User user ){
        this.address=address;
        this.type=type;
        this.floor=floor;
        this.rooms=rooms;
        this.numberOfHouse=numberOfHouse;
        this.state=state;
        this.price=price;
        this.user=user;
    }



    public String toString() {
        if(floor==-1){
            return  type + "- for " + state + ":" + rooms + "rooms" + "\n" + price +"$" + "\n" + "contact info " + user;

        }
        else {
            return  type + "- for " + state + ":" + rooms + "rooms, floor" + floor + "\n" + price +"$" + "\n" + "contact info " + user;

        }

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getRooms() {
        return rooms;
    }

    public void setRooms(double rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}