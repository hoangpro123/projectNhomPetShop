package tdc.edu.vn.project;

/**
 * Created by Aws on 28/01/2018.
 */

public class Pet {

    private String Title;
    private String Price ;
    private String Description ;
    private int Thumbnail ;

    public Pet() {
    }

    public Pet(String title, String price, String description, int thumbnail) {
        Title = title;
        Price = price;
        Description = description;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setPrice(String category) {
        Price = Price;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
