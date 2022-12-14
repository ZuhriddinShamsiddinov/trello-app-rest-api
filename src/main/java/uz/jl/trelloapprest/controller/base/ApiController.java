package uz.jl.trelloapprest.controller.base;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 22/08/22/09:24 (Monday)
 */
public abstract class ApiController<S> {
    protected static final String API = "/api";
    protected static final String VERSION = "/v1";
    protected static final String PATH = API + VERSION;
    protected final S service;

    protected ApiController(S service) {
        this.service = service;
    }
}
