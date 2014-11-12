package com.oodd.spring.manytomanyselfreferencewithjoinattribute.mock;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto.WorkerWorkerDto;

@Controller
@RequestMapping(value="/manytomanyselfreferencewithjoinattribute/workerworker/mock")
public class WorkerWorkerMockController {
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody List<WorkerWorkerDto> findAll(){
		return WorkerWorkerInMemoryDB.INSTANCE.findAll();
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody WorkerWorkerDto workerWorkerDto){
		WorkerWorkerInMemoryDB.INSTANCE.add(workerWorkerDto);
	}
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remove(@RequestBody WorkerWorkerDto workerWorkerDto){
		WorkerWorkerInMemoryDB.INSTANCE.remove(workerWorkerDto);
	}
	@RequestMapping(value="/isPresent", method=RequestMethod.POST)	
	public @ResponseBody boolean isPresent(@RequestBody WorkerWorkerDto workerWorkerDto){	
		return WorkerWorkerInMemoryDB.INSTANCE.isPresent(workerWorkerDto);
	}
}
