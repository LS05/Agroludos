package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AgroRequestContext;

public interface ACFactoryI {
	AgroludosAC getAC();
	void setAgroRequestContext(AgroRequestContext req);
}
