
package acme.features.authenticated.culp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.culps.Culp;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/culp/")
public class AuthenticatedCulpController extends AbstractController<Authenticated, Culp> {

	@Autowired
	private AuthenticatedCulpShowService showService;


	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
