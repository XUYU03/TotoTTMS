package xupt.se.ttms.idao;

import xupt.se.ttms.dao.*;
import xupt.se.ttms.model.Employee;

public class DAOFactory
{
    private static iStudioDAO stuDao;


    private  static  iPlayDAO playDAO;

    private  static  iCustomerDAO customerDAO;

    private  static  iEmployee eemployeeDAO;
    private static iScheduleDAO scheduleDAO;

//    private  static iUsr_RoleDAO usrRoleDAO;

    private static  iSaleDAO saleDAO;

    private static iResourceDAO resourceDAO;

    private static iRolesDAO rolesDAO;

    private static iSeatDAO seDao;
    public static synchronized iSeatDAO creatSeatDAO()
    {
        if(null == seDao)
            seDao=new SeatDAO();
        return seDao;
    }
    public static synchronized iResourceDAO creatResourceDAO(){
        if(null ==resourceDAO)
            resourceDAO=new ResourceDAO();
        return  resourceDAO;
    }
    public static synchronized iStudioDAO creatStudioDAO()
    {
        if(null == stuDao)
            stuDao=new StudioDAO();
        return stuDao;
    }

    public static  synchronized  iPlayDAO createPlayDAO(){
        if(null == playDAO)
            playDAO = new PlayDAO();
        return playDAO;
    }
    public static synchronized iCustomerDAO creatCustomerDAO()
    {
        if(null == customerDAO)
            customerDAO=new customerDAO();
        return customerDAO;
    }

    public static synchronized iEmployee creatEmployeeDAO()
    {
        if(null == eemployeeDAO)
            eemployeeDAO=new employeeDAO();
        return eemployeeDAO;
    }

    public static synchronized iScheduleDAO creatScheduleDAO() {
        if(null == scheduleDAO)
            scheduleDAO=new ScheduleDAO();
        return scheduleDAO;
    }
//    public static synchronized iUsr_RoleDAO creatUsr_RoleDAO(){
//        if(null == usrRoleDAO)
//            usrRoleDAO=new Usr_RoleDAO();
//        return usrRoleDAO;
//    }
    public static synchronized iSaleDAO creatSalesDAO()
    {
        if(null == saleDAO)
            saleDAO=new SaleDAO();
        return saleDAO;
    }
    public static synchronized iRolesDAO creatRolesDAO(){
        if(null == rolesDAO)
            rolesDAO=new RolesDAO();
        return rolesDAO;
    }
}
