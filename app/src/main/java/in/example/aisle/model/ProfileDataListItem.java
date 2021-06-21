package in.example.aisle.model;

import java.util.List;

public class ProfileDataListItem{
	private List<PreferencesItem> preferences;
	private String question;
	private String invitationType;

	public List<PreferencesItem> getPreferences(){
		return preferences;
	}

	public String getQuestion(){
		return question;
	}

	public String getInvitationType(){
		return invitationType;
	}
}