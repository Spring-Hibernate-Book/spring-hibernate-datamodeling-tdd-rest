package com.oodd.spring.manytomanyselfreferencewithjoinattribute.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerDto;


@Controller
@RequestMapping(value="/manytomanyselfreferencewithjoinattribute/worker/mock")
public class WorkerMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<WorkerDto> findAll(){
		return WorkerInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/findById/{workerId}", method=RequestMethod.GET)
	public @ResponseBody WorkerDto findById(@PathVariable("workerId") Integer workerId){
		return WorkerInMemoryDB.INSTANCE.findById(workerId);
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody WorkerDto workerDto){
		WorkerInMemoryDB.INSTANCE.add(workerDto);
	}
	@RequestMapping(value="/remove/{workerId}", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("workerId") Integer workerId){
		WorkerInMemoryDB.INSTANCE.remove(workerId);
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestBody WorkerDto workerDto){
		WorkerInMemoryDB.INSTANCE.edit(workerDto);
	}
}
