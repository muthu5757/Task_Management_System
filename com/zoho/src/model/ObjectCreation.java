package src.model;

import src.controller.*;
import src.controller.HR;
import src.view.*;

import java.sql.Connection;
import java.util.Scanner;

public class ObjectCreation {
    private static Scanner in = null;
    private static DBConnection db = null;
    private static SuperAdmin_Helper h = null;

    private static Admin_Helper ah = null;
    private static HR_Helper hh = null;
    private static Selection s = null;
    private static Insertion ins = null;

    private static HR hr = null;
    private static Display d = null;

    public static Scanner getInstanceofScanner()
    {
        if(in==null)
            in = new Scanner(System.in);
        return in;
    }
    public static Selection getInstanceofSelection()
    {
        if(s==null)
            s = new Selection();
        return s;
    }
    public static Connection getInstanceofDatabaseConnection()
    {
        if(db==null)
            db = DBConnection.Connection();
        return db.connect();
    }
    public static void closeDatabaseConnection()
    {
        if(db==null)
           return;
        db.closeConnect();
    }
    public static SuperAdmin_Helper getInstanceofSuperAdmin_Helper()
    {
        if(h==null)
            h = new SuperAdmin_Helper();
        return h;
    }
    public static Admin_Helper getInstanceofAdmin_Helper()
    {
        if(ah==null)
            ah = new Admin_Helper();
        return ah;
    }
    public static HR_Helper getInstanceofHR_Helper()
    {
        if(hh==null)
            hh = new HR_Helper();
        return hh;
    }
    public static Insertion getInstanceofInsertion()
    {
        if(ins==null)
            ins = new Insertion();
        return ins;
    }
    public static Display getInstanceofDisplay()
    {
        if(d==null)
            d = new Display();
        return d;
    }
    public static HR getInstanceofHR()
    {
        if(hr==null)
            hr = new HR();
        return hr;
    }
}
