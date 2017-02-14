package com.gooagoo.pos.plugin.agent;
import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class AttachAgent {
  static class AttachThread extends Thread {

    private final List<VirtualMachineDescriptor> listBefore;

    private final String jar;

    AttachThread(String attachJar, List<VirtualMachineDescriptor> vms) {
      listBefore = vms;
      jar = attachJar;
    }

    public void run() {
      VirtualMachine vm = null;
      List<VirtualMachineDescriptor> listAfter = null;
      try {
        int count = 0;
        while (true) {
          listAfter = VirtualMachine.list();
          for (VirtualMachineDescriptor vmd : listAfter) {
            
            System.out.println("displayName:" + vmd.displayName());
            System.out.println("toString:" + vmd.toString());
            
            
            if (!listBefore.contains(vmd)) {
              vm = VirtualMachine.attach(vmd);
              break;
            }
          }
          Thread.sleep(100);
          count++;
          if (null != vm || count >= 1000) {
            break;
          }
        }
        vm.loadAgent(jar);
        vm.detach();
      } catch (Exception e) {
    	 System.out.println(e.toString());
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    new AttachThread("C:\\Users\\gag\\Desktop\\javaagent.jar", VirtualMachine.list()).start();
    //new AttachThread("/home/admin/attach/agent.jar", VirtualMachine.list()).start();
  }
}