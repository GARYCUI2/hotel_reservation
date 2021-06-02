package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {

        super(roomNumber, 0.0, enumeration);
    }

    @Override
    public String getRoomNumber() {
        return super.getRoomNumber();
    }

    @Override
    public RoomType getRoomType() {
        return super.getRoomType();
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomType=" + getRoomType() +
                ", isFree=" + isFree() +
                '}';
    }

    @Override
    public Double getRoomPrice() {
        return super.getRoomPrice();
    }

    @Override
    public boolean isFree() {
        return true;
    }
}
