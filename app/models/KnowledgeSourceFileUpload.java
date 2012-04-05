package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Shellenbarger
 * Date: 2/5/12
 * Time: 12:47 PM
 *
 * This represents a Knowledge Source File
 */
@Entity
public class KnowledgeSourceFileUpload extends Model {

    @Id
    public Long id;
    public String name;
    @Lob
    public byte[] file;
    public static Finder<Long,KnowledgeSourceFileUpload> find = new Finder<Long, KnowledgeSourceFileUpload>(Long.class,KnowledgeSourceFileUpload.class);
}
