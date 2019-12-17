
package acme.features.authenticated.member;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.members.Member;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/member")
public class AuthenticatedMemberController extends AbstractController<Authenticated, Member> {

	@Autowired
	AuthenticatedMemberListService		listService;

	@Autowired
	AuthenticatedMemberShowService		showService;

	@Autowired
	AuthenticatedMemberCreateService	createService;

	@Autowired
	AuthenticatedMemberDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
