package za.co.payguru.model;

import org.json.JSONObject;

public class CompanyUser {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";
	
	public static final String DEFAULT_USER_PERMISSIONS = "00000000000000100";
	public static final String ADMIN_USER_PERMISSIONS = "11111111111111111111";
	
	public static final int USER_TYPE_SYSTEM = 1;
	public static final int USER_TYPE_USER = 2;

	//010101010
	//product edit,customer edit,reps edit,invoices edit,payment unmatched edit,payment transfers edit,bank edit,reports view,profile edit
	private int compId = 0;
	private int userId = 0;
	private String userName = "";
	private String userSurname = "";
	private String userCellNo = "";
	private String userTelNo = "";
	private String userEmail = "";
	private String userPassword = "";
	private String userLoginDateTime = "";
	private String userActive = "";
	private int userType = 0;
	private int profileId = 0;
	private String userPermissions = DEFAULT_USER_PERMISSIONS;
	public CompanyUser(int compId, int userId, String userName, String userSurname, String userCellNo, String userTelNo,
			String userEmail, String userPassword, String userLoginDateTime, String userActive, int userType, int profileId,
			String userPermissions) {
		super();
		this.compId = compId;
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userCellNo = userCellNo;
		this.userTelNo = userTelNo;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userLoginDateTime = userLoginDateTime;
		this.userActive = userActive;
		this.userType = userType;
		this.profileId = profileId;
		this.userPermissions = userPermissions;
	}
	public CompanyUser() {
		this.compId = 0;
		this.userId = 0;
		this.userName = "";
		this.userSurname = "";
		this.userCellNo = "";
		this.userTelNo = "";
		this.userEmail = "";
		this.userPassword = "";
		this.userLoginDateTime = "";
		this.userActive = "";
		this.userType = 0;
		this.profileId = 0;
		this.userPermissions = DEFAULT_USER_PERMISSIONS;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	public String getUserCellNo() {
		return userCellNo;
	}
	public void setUserCellNo(String userCellNo) {
		this.userCellNo = userCellNo;
	}
	public String getUserTelNo() {
		return userTelNo;
	}
	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserLoginDateTime() {
		return userLoginDateTime;
	}
	public void setUserLoginDateTime(String userLoginDateTime) {
		this.userLoginDateTime = userLoginDateTime;
	}
	public String getUserActive() {
		return userActive;
	}
	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getUserPermissions() {
		return userPermissions;
	}
	public void setUserPermissions(String userPermissions) {
		this.userPermissions = userPermissions;
	}
	@Override
	public String toString() {
		return "CompanyUser [compId=" + compId + ", userId=" + userId + ", userName=" + userName + ", userSurname="
				+ userSurname + ", userCellNo=" + userCellNo + ", userTelNo=" + userTelNo + ", userEmail=" + userEmail
				+ ", userPassword=" + userPassword + ", userLoginDateTime=" + userLoginDateTime + ", userActive=" + userActive
				+ ", userType=" + userType + ", profileId=" + profileId + ", userPermissions=" + userPermissions + "]";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("compid", compId);
			json.put("userid", userId);
			json.put("username", userName);
			json.put("usersurname", userSurname);
			json.put("usercellno", userCellNo);
			json.put("usertelno", userTelNo);
			json.put("useremail", userEmail);
			json.put("userpassword", userPassword);
			json.put("userlogindatetime", userLoginDateTime);
			json.put("useractive", userActive);
			json.put("usertype", userType);
			json.put("profileid", profileId);
			json.put("userpermissions", userPermissions);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
}