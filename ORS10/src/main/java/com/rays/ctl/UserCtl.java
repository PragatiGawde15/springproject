package com.rays.ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropDownList;
import com.rays.common.ORSResponse;
import com.rays.common.attachment.AttachmentDTO;
import com.rays.common.attachment.AttachmentServiceInt;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@Autowired
	public AttachmentServiceInt attachmentService;

	@GetMapping("preLoad")
	public ORSResponse preLoad() {

		ORSResponse res = new ORSResponse();

		UserDTO dto = new UserDTO();

		List<DropDownList> list = baseService.search(dto, 0, 0);

		res.addResult("userPreload", list);

		return res;

	}

	@PostMapping("profilePic/{userId}")
	public ORSResponse uploadPic(@RequestParam("file") MultipartFile file, @PathVariable Long userId) {

		ORSResponse res = new ORSResponse();
		AttachmentDTO doc = new AttachmentDTO(file);

		UserDTO dto = new UserDTO();

		dto = baseService.findByPk(userId);
		doc.setUserId(userId);

		if (dto.getImageId() != null && dto.getImageId() > 0) {
			doc.setId(dto.getImageId());
		}

		long imageId = attachmentService.save(doc);

		if (dto.getImageId() == null) {
			dto.setImageId(imageId);
			baseService.update(dto);
		}
		res.addData(imageId);
		doc.setDescription("Profile Pic");
		res.addMessage("Image upload successfully...!!!");

		return res;

	}

	/*
	 * @PostMapping("profilePic") public ORSResponse uploadPic(@RequestParam("file")
	 * MultipartFile file) {
	 * 
	 * ORSResponse res = new ORSResponse();
	 * 
	 * AttachmentDTO doc = new AttachmentDTO(file);
	 * 
	 * doc.setDescription("profile pic");
	 * 
	 * long imageId = attachmentService.add(doc);
	 * 
	 * res.addData(imageId);
	 * 
	 * res.addMessage("Image upload successfully...!!!");
	 * 
	 * return res;
	 * 
	 * }
	 */

	@GetMapping("profilePic/{imageId}")
	public void downloadPic(@PathVariable long imageId, HttpServletResponse response) throws IOException {

		AttachmentDTO doc = attachmentService.findByPk(imageId);

		response.setContentType(doc.getType());

		OutputStream out = response.getOutputStream();

		out.write(doc.getDoc());

		out.close();

	}

}
