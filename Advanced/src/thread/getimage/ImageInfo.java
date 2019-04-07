package thread.getimage;

public class ImageInfo {
	private String img;

	private String id;

	private String time;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ImageInfo [img = " + img + ", id = " + id + ", time = " + time + "]";
	}
}
