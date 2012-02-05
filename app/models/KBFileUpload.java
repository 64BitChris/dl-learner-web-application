package models;

import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Shellenbarger
 * Date: 2/5/12
 * Time: 12:47 PM
 *
 * This represents a Knowledge Source File
 */
@Entity
public class KBFileUpload extends Model {

    public String name;
    public Blob file;
    
}
