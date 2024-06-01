package example.core;

import example.service.BaseSandboxPaypalService;
import example.service.SandboxPaypalService;

public class ServiceInjection {

    public SandboxPaypalService sandboxPaypalService = new SandboxPaypalService();
    public BaseSandboxPaypalService baseSandboxPaypalService = new BaseSandboxPaypalService();
    public BaseService baseService = new BaseService();

}
