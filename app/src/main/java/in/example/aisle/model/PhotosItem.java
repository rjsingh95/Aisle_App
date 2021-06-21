package in.example.aisle.model;

public class PhotosItem{
	private int photoId;
	private String photo;
	private boolean selected;
	private String status;

	public int getPhotoId(){
		return photoId;
	}

	public String getPhoto(){
		return photo;
	}

	public boolean isSelected(){
		return selected;
	}

	public String getStatus(){
		return status;
	}
}
