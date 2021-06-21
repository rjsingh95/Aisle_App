package in.example.aisle.model;

import java.util.List;

public class ProfileModel{
	private Object latestPoll;
	private List<PreferencesItem> preferences;
	private String lng;
	private String lastSeen;
	private Object pollInfo;
	private Work work;
	private String lastSeenWindow;
	private boolean hasActiveSubscription;
	private List<Object> userInterests;
	private String verificationStatus;
	private List<PhotosItem> photos;
	private boolean showConciergeBadge;
	private double approvedTime;
	private General_information general_information;
	private List<ProfileDataListItem> profileDataList;
	private Object instagramImages;
	private int onlineCode;
	private boolean isFacebookDataFetched;
	private Object icebreakers;
	private Object meetup;
	private String lat;
	private Object story;

	public Object getLatestPoll(){
		return latestPoll;
	}

	public List<PreferencesItem> getPreferences(){
		return preferences;
	}

	public String getLng(){
		return lng;
	}

	public String getLastSeen(){
		return lastSeen;
	}

	public Object getPollInfo(){
		return pollInfo;
	}

	public Work getWork(){
		return work;
	}

	public String getLastSeenWindow(){
		return lastSeenWindow;
	}

	public boolean isHasActiveSubscription(){
		return hasActiveSubscription;
	}

	public List<Object> getUserInterests(){
		return userInterests;
	}

	public String getVerificationStatus(){
		return verificationStatus;
	}

	public List<PhotosItem> getPhotos(){
		return photos;
	}

	public boolean isShowConciergeBadge(){
		return showConciergeBadge;
	}

	public double getApprovedTime(){
		return approvedTime;
	}

	public General_information getGeneral_information(){
		return general_information;
	}

	public List<ProfileDataListItem> getProfileDataList(){
		return profileDataList;
	}

	public Object getInstagramImages(){
		return instagramImages;
	}

	public int getOnlineCode(){
		return onlineCode;
	}

	public boolean isIsFacebookDataFetched(){
		return isFacebookDataFetched;
	}

	public Object getIcebreakers(){
		return icebreakers;
	}

	public Object getMeetup(){
		return meetup;
	}

	public String getLat(){
		return lat;
	}

	public Object getStory(){
		return story;
	}
}