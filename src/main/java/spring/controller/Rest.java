package spring.controller;

import java.util.Date;
import java.util.List;

import spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;

import spring.dao.RequestsDAO;

@RestController
public class Rest {

	
	@Autowired
	private RequestsDAO requestsDAO;

	/* !!!! Загрузка FrontEnd !!!! */
	/*
	@CrossOrigin
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String getjsp(){

		return "index method";
	}*/


	/* !!!! Получение последнего номера заявки !!!!*/
	@CrossOrigin
	@GetMapping("requests/lastid")
	public ResponseEntity getLastId(){
			return new ResponseEntity(requestsDAO.getLastId(),HttpStatus.OK);

	}


	/* !!!! Заявки на инкассацию !!!! */
	@CrossOrigin
	@GetMapping("/requests")
	public List getRequests(){
		return requestsDAO.getReqs();
	}



	@CrossOrigin
	@GetMapping("/requests/{id}")
	public ResponseEntity getReq(@PathVariable("id") int id){
		Request obj = requestsDAO.getReq(id);
		return new ResponseEntity(obj,HttpStatus.OK);

	}

	@CrossOrigin
	@PostMapping("/requests/{id}/bank/{bank}/inn/{inn}/kpp/{kpp}/nameOrganization/{nameOrganization}/ogrn/{ogrn}/nameEmploye/{nameEmploye}/telephoneEmploye/{telephoneEmploye}/bankDetails/{bankDetails}/accountNumber/{accountNumber}/bik/{bik}/bankNumber/{bankNumber}/nameBank/{nameBank}/swift/{swift}")
	public ResponseEntity createReq(@PathVariable("id") int id, @PathVariable("bank") String bank, @PathVariable("inn") String inn,
								  @PathVariable("kpp") String kpp, @PathVariable("nameOrganization") String nameOrganization, @PathVariable("ogrn") String ogrn,
								  @PathVariable("nameEmploye") String nameEmploye, @PathVariable("telephoneEmploye") String telephoneEmploye,
								  @PathVariable("bankDetails") String bankDetails, @PathVariable("accountNumber") String accountNumber, @PathVariable("bik") String bik,
								  @PathVariable("bankNumber") String bankNumber, @PathVariable("nameBank") String nameBank, @PathVariable("swift") String swift){
		Request obj = new Request(id,bank,inn,kpp,nameOrganization,ogrn,nameEmploye,telephoneEmploye,bankDetails,accountNumber,bik,bankNumber,nameBank,swift);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		obj.addHidden(format.format(new Date()),"1","1");
		requestsDAO.createReq(obj);
		return new ResponseEntity(obj,HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping("/requests/{id}/bank/{bank}/inn/{inn}/kpp/{kpp}/nameOrganization/{nameOrganization}/ogrn/{ogrn}/nameEmploye/{nameEmploye}/telephoneEmploye/{telephoneEmploye}/bankDetails/{bankDetails}/accountNumber/{accountNumber}/bik/{bik}/bankNumber/{bankNumber}/nameBank/{nameBank}/swift/{swift}")
	public ResponseEntity updateReq(@PathVariable("id") int id, @PathVariable("bank") String bank, @PathVariable("inn") String inn,
									@PathVariable("kpp") String kpp, @PathVariable("nameOrganization") String nameOrganization, @PathVariable("ogrn") String ogrn,
									@PathVariable("nameEmploye") String nameEmploye, @PathVariable("telephoneEmploye") String telephoneEmploye,
									@PathVariable("bankDetails") String bankDetails, @PathVariable("accountNumber") String accountNumber, @PathVariable("bik") String bik,
									@PathVariable("bankNumber") String bankNumber, @PathVariable("nameBank") String nameBank, @PathVariable("swift") String swift) {

		Request obj = new Request(id,bank,inn,kpp,nameOrganization,ogrn,nameEmploye,telephoneEmploye,bankDetails,accountNumber,bik,bankNumber,nameBank,swift);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		obj.addHidden(format.format(new Date()),"1","2");
		requestsDAO.updateReq(obj);

		return new ResponseEntity(obj, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping("/requests/{id}")
	public String deleteReq(@PathVariable("id") int id){
		requestsDAO.deleteReq(id);
		return "Deleted";
	}


	/* !!!! Объекты инкассации !!!! */
	@CrossOrigin
	@GetMapping("/requests/objects")
	public List getObjects(){
		return requestsDAO.getObjects();
	}

	@CrossOrigin
	@GetMapping("/requests/objects/{id}")
	public List getObjIncas(@PathVariable("id") int id){
		ObjIncas obj = requestsDAO.getObjIncas(id);
		List<ObjIncas> res = requestsDAO.getObjectsById(id);
		return res;

	}

	@CrossOrigin
	@PostMapping("/requests/objects/{id}/time/{time}/type/{type}/period/{period}/day/{day}/money/{money}/code/{code}/head/{head}/date/{date}")
	public ResponseEntity createObjIncas(@PathVariable("id") int id, @PathVariable("time") String time, @PathVariable("type") String type,
									  @PathVariable("period") String period, @PathVariable("day") String day, @PathVariable("money") String money,
									  @PathVariable("code") String code, @PathVariable("head") String head, @PathVariable("date") String date){
		ObjIncas tmp = new ObjIncas(id,time,type,period,day,money,code,head,date);
		requestsDAO.createObjIncas(tmp);
		return new ResponseEntity(tmp,HttpStatus.OK);

	}

}