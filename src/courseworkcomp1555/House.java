package courseworkcomp1555;

// COMMENTED
public class House {

    //Class that contains all the elements that a house can have. A bluePrint of a single house 
    private int id;
    private double price;
    private double numberBathrooms;
    private double area;
    private double livingSpace;
    private int numberGarages;
    private int numberRooms;
    private int numberBedrooms;
    private int age;

    public House(int id, double price, double numberBathrooms, double area,
            double livingSpace, int numberGarages, int numberRooms,
            int numberBedrooms, int age) {

        this.id = id;
        this.price = price;
        this.numberBathrooms = numberBathrooms;
        this.area = area;
        this.livingSpace = livingSpace;
        this.numberGarages = numberGarages;
        this.numberRooms = numberRooms;
        this.numberBedrooms = numberBedrooms;
        this.age = age;

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the numberBathrooms
     */
    public double getNumberBathrooms() {
        return numberBathrooms;
    }

    /**
     * @param numberBathrooms the numberBathrooms to set
     */
    public void setNumberBathrooms(double numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the livingSpace
     */
    public double getLivingSpace() {
        return livingSpace;
    }

    /**
     * @param livingSpace the livingSpace to set
     */
    public void setLivingSpace(double livingSpace) {
        this.livingSpace = livingSpace;
    }

    /**
     * @return the numberGarages
     */
    public int getNumberGarages() {
        return numberGarages;
    }

    /**
     * @param numberGarages the numberGarages to set
     */
    public void setNumberGarages(int numberGarages) {
        this.numberGarages = numberGarages;
    }

    /**
     * @return the numberRooms
     */
    public int getNumberRooms() {
        return numberRooms;
    }

    /**
     * @param numberRooms the numberRooms to set
     */
    public void setNumberRooms(int numberRooms) {
        this.numberRooms = numberRooms;
    }

    /**
     * @return the numberBedrooms
     */
    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    /**
     * @param numberBedrooms the numberBedrooms to set
     */
    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

}
