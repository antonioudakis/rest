package org.ntua.tant.rest.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Node {
	
	private long id;
	private long gis_id;
	private String owner;
	private String type;
	private String material;
	private String technology;
	private String speed;
	private Date installDate;
	private String comment;
	
	
	public Node(){
		
	}
	
	public Node(long id, long gis_id, String owner, String type, String material, String technology, String speed, Date installDate, String comment) {
		this.id = id;
		this.gis_id = gis_id;
		this.owner = owner;
		this.type = type;
		this.material = material;
		this.technology = technology;
		this.speed = speed;
		this.installDate = installDate;
		this.comment = comment;
	}
	
	public void printNode(){
		System.out.println( "id = " + id );
        System.out.println( "gis_id = " + gis_id );
        System.out.println( "owner = " + owner );
        System.out.println( "type = " + type );
        System.out.println( "material = " + material );
        System.out.println( "technology = " + technology );
        System.out.println( "speed = " + speed );
        System.out.println( "installDate = " + installDate.toString() );
        System.out.println( "comment = " + comment );
	}
	
	public String getNodeVariables(){
		String variables = "(gis_id";
		if (owner!=null) {
			variables+=",owner";
		}
		if (type!=null) {
			variables+=",type";
		}
		if (material!=null) {
			variables+=",material";
		}
		if (technology!=null) {
			variables+=",technology";
		}
		if (speed!=null) {
			variables+=",speed";
		}
		if (installDate!=null) {
			variables+=",installdate";
		}
		if (comment!=null) {
			variables+=",comment";
		}
		variables+=")";
		return variables;
	}
	
	public String getNodeValues(){
		String values = "("+gis_id;
		if (owner!=null)
			values+=",'"+owner+"'";
		if (type!=null)
			values+=",'"+type+"'";
		if (material!=null)
			values+=",'"+material+"'";
		if (technology!=null)
			values+=",'"+technology+"'";
		if (speed!=null)
			values+=",'"+speed+"'";
		if (installDate!=null)
			values+=",'"+installDate.toString()+"'";
		if (comment!=null)
			values+=",'"+comment+"'";
		values+=")";
		return values;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGis_id() {
		return gis_id;
	}

	public void setGis_id(long gis_id) {
		this.gis_id = gis_id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}


