package com.oodd.spring.singletableinheritance.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.singletableinheritance.dto.ProtocolDto;
import com.oodd.spring.singletableinheritance.dto.SNMPDto;
import com.oodd.spring.singletableinheritance.dto.TCPDto;
import com.oodd.spring.singletableinheritance.entity.Protocol;
import com.oodd.spring.singletableinheritance.entity.SNMP;
import com.oodd.spring.singletableinheritance.entity.TCP;

@Component
public class ProtocolMapper {

	public Protocol mapDtoToEntity(ProtocolDto protocolDto){
		if(protocolDto instanceof TCPDto) {
			TCP tcp = new TCP();
			if(null!=protocolDto.getId()) tcp.setId(protocolDto.getId());
			if(null!=protocolDto.getName()) tcp.setName(protocolDto.getName());			
			return tcp;
		} else if(protocolDto instanceof SNMPDto) {
			SNMP snmp = new SNMP();
			if(null!=protocolDto.getId()) snmp.setId(protocolDto.getId());
			if(null!=protocolDto.getName()) snmp.setName(protocolDto.getName());	
			return snmp;
		}
		return null;
	}
	public ProtocolDto mapEntityToDto(Protocol protocol){		
		if(protocol instanceof TCP) {
			TCPDto tcpDto = new TCPDto();
			if(null!=protocol.getId()) tcpDto.setId(protocol.getId());
			if(null!=protocol.getName()) tcpDto.setName(protocol.getName());
			return tcpDto;
		} else if(protocol instanceof SNMP) {
			SNMPDto snmpDto = new SNMPDto();
			if(null!=protocol.getId()) snmpDto.setId(protocol.getId());
			if(null!=protocol.getName()) snmpDto.setName(protocol.getName());
			return snmpDto;
		}	
		return null;
	}
}