package agroludos.presentation.controller;

import agroludos.presentation.reqh.AgroRequestContext;

public interface ACFactory {
	ApplicationController getAC(AgroRequestContext req);
}