package agroludos.presentation.controller;

import agroludos.presentation.reqh.AgroRequestContext;

public interface ACFactoryI {
	ApplicationController getAC(AgroRequestContext req);
}