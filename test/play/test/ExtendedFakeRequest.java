package play.test;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/8/12
 * Time: 12:44 PM
 * <p/>
 * Extend the Play Java FakeRequest so we can leverage it with testing.
 */
public class ExtendedFakeRequest extends FakeRequest {

    /**
     * Constructs a new GET / fake request.
     */
    public ExtendedFakeRequest() {
        super();
    }

    /**
     * Constructs a new request.
     */
    public ExtendedFakeRequest(String method, String path) {
        super(method, path);
    }

    /**
     * Add a body object to the Fake Request.
     *
     * @param body The Body Object.
     * @return A Fake Request with the given body object.
     */
    public FakeRequest withBody(Object body) {
        fake = new play.api.test.FakeRequest(fake.method(), fake.uri(), fake.headers(), body);
        return this;
    }
}
