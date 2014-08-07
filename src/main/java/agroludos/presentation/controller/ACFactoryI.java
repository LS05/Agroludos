package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AgroRequestContext;

public interface ACFactoryI {
	ApplicationController getAC(AgroRequestContext req);
}