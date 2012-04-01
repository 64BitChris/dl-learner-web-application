package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import java.sql.Blob;

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
