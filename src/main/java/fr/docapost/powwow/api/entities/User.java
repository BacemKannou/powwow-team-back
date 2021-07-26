package fr.docapost.powwow.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "MY_USER")
public class User {

	@Id @GeneratedValue
	private Long id;
	private String userName;
	private String password;
	private boolean actived;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<Role>();

	@OneToOne(
			cascade = {CascadeType.ALL},
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = "PICTURE_MODEL_ID"
	)
	private PictureModel pictureModel;

	public User() {
		
	}
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password, Collection<Role> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the actived
	 */
	public boolean isActived() {
		return actived;
	}
	/**
	 * @param actived the actived to set
	 */
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public PictureModel getPictureModel() {
		return pictureModel;
	}

	public void setPictureModel(PictureModel pictureModel) {
		this.pictureModel = pictureModel;
	}
}
