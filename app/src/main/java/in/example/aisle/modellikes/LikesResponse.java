package in.example.aisle.modellikes;

import java.util.List;

public class LikesResponse{
	private int likesReceivedCount;
	private List<ProfilesItem> profiles;
	private boolean canSeeProfile;

	public int getLikesReceivedCount(){
		return likesReceivedCount;
	}

	public List<ProfilesItem> getProfiles(){
		return profiles;
	}

	public boolean isCanSeeProfile(){
		return canSeeProfile;
	}
}