package fi.polarshift.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.XhtmlException;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.xhtml.api.MacroDefinition;
import com.atlassian.confluence.xhtml.api.MacroDefinitionHandler;
import com.atlassian.confluence.xhtml.api.XhtmlContent;
import com.atlassian.confluence.userstatus.FavouriteManager;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;

import java.util.Map;

public class InitFavMacro implements Macro {

    private static final String INIT_KEY = "INFO";

    private final FavouriteManager favouriteManager;
    private final SpaceManager spaceManager;

    public InitFavMacro(FavouriteManager favouriteManager, SpaceManager spaceManager) {
        this.favouriteManager = favouriteManager;
	this.spaceManager = spaceManager;
    }
    @Override
	public String execute(Map<String, String> parameters, String bodyContent, 
			      ConversionContext conversionContext) throws MacroExecutionException {

	Space initSpace = spaceManager.getSpace(INIT_KEY);

	if(!favouriteManager.isUserFavourite(com.atlassian.confluence.user.AuthenticatedUserThreadLocal.getUser(),initSpace))
	    favouriteManager.addSpaceToFavourites(com.atlassian.confluence.user.AuthenticatedUserThreadLocal.getUser(),initSpace);
	
	return "";	    
    }
    
    @Override
	public BodyType getBodyType() {
        return BodyType.NONE;
    }

    @Override
	public OutputType getOutputType() {
        return OutputType.BLOCK;
    }
}
