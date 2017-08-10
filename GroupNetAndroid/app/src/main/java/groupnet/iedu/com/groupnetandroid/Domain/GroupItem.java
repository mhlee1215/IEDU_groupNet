package groupnet.iedu.com.groupnetandroid.Domain;

import android.graphics.Bitmap;

public class GroupItem {
    private Bitmap image;
	private String title;

	public GroupItem(Bitmap image, String title) {
		super();
		this.image = image;
		this.title = title;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}