package teste;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import Controller.Operations;
import Model.Polynomial;
//import org.junit.Tests;

public class Test {
	private static int nrTesteExecutate;
	private static int nrTesteCuSucces;
	private static Operations op;
	private static Polynomial p1;
	private static Polynomial p2;
	private static String polinom1;
	private static String polinom2;
	
	public Test() throws Exception {
		polinom1 = "3x^3+2x+1";
		polinom2 = "4x+10";
		p1 = new Polynomial(polinom1);
		p2 = new Polynomial(polinom2);
	}
	
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Instantiere operatiilor");
		op = new Operations();
	}
	
	public static void tearDownAfterClass() throws Exception {
		System.out.println("S-au executat " + nrTesteExecutate + " teste, din care " + nrTesteCuSucces + " s-au terminat cu succes.");
	}
	
	public void setUp() throws Exception {
		System.out.println("Test nou!");
		nrTesteExecutate++;
	}
	
	public void tearDown() throws Exception {
		System.out.println("S-a terminat testul!");
	}
	
	public void testAddition() { // p1+p2
		String p3 = new String();
		Polynomial p4 = new Polynomial();
		try {
			String polinom = "3x^3+6x+11";
			p4 = new Polynomial(polinom);
		} catch (Exception e) {
			System.out.println("Polinom invalid");
		}
		
		op.setTotal(p2);
		op.addTo(p1);
		p3 = op.getValue();
		System.out.println("Rezultatul calculat: " + p3);
		System.out.println("Rezultatul corect: " + p4);
		assert(p3.equals(p4));
		nrTesteCuSucces++;
	}
	
	public void testSubtraction() { // p1-p2
		String p3 = new String();
		Polynomial p4 = new Polynomial();
		try {
			String polinom = "3x^3-2x-9";
			p4 = new Polynomial(polinom);
		} catch (Exception e) {
			System.out.println("Polinom invalid");
		}
		
		op.setTotal(p1);
		op.subtractFrom(p2);
		p3 = op.getValue();
		System.out.println("Rezultatul calculat: " + p3);
		System.out.println("Rezultatul corect: " + p4);
		assert(p3.equals(p4));
		nrTesteCuSucces++;
	}
	
	public void testMultiplication() throws Exception { // p1*p2
		String p3 = new String();
		Polynomial p4 = new Polynomial();
		try {
			String polinom = "12x^4+30x^3+8x^2+24x+10";
			p4 = new Polynomial(polinom);
		} catch (Exception e) {
			System.out.println("Polinom invalid");
		}
		
		op.setTotal(p1);
		op.multiplyBy(p2);
		p3 = op.getValue();
		System.out.println("Rezultatul calculat: " + p3);
		System.out.println("Rezultatul corect: " + p4);
		assert(p3.equals(p4));
		nrTesteCuSucces++;
	}
	
	public void testDivision() { // p1/p2 !!!!!!!!!!!!!
		String p3 = new String();
		Polynomial p4 = new Polynomial();
		try {
			String polinom = "12x^4+30x^3+8x^2+24x+10";
			p4 = new Polynomial(polinom);
		} catch (Exception e) {
			System.out.println("Polinom invalid");
		}
		
		op.setTotal(p1);
		op.subtractFrom(p2);
		p3 = op.getValue();
		System.out.println("Rezultatul calculat: " + p3);
		System.out.println("Rezultatul corect: " + p4);
		assert(p3.equals(p4));
		nrTesteCuSucces++;
	}
	
	public void testDerivation() { // p1'
		String p3 = new String();
		Polynomial p4 = new Polynomial();
		try {
			String polinom = "9x^2+2";
			p4 = new Polynomial(polinom);
		} catch (Exception e) {
			System.out.println("Polinom invalid");
		}
		
		op.setTotal(p1);
		op.derivate(p1);
		p3 = op.getValue();
		System.out.println("Rezultatul calculat: " + p3);
		System.out.println("Rezultatul corect: " + p4);
		assert(p3.equals(p4));
		nrTesteCuSucces++;
	}
	
	public void testIntegration() { // S(p1)
		String p3 = new String();
		Polynomial p4 = new Polynomial();
		try {
			String polinom = "0.75x^4+x^2+x";
			p4 = new Polynomial(polinom);
		} catch (Exception e) {
			System.out.println("Polinom invalid");
		}
		
		op.setTotal(p1);
		op.integrate(p1);
		p3 = op.getValue();
		System.out.println("Rezultatul calculat: " + p3);
		System.out.println("Rezultatul corect: " + p4);
		assert(p3.equals(p4));
		nrTesteCuSucces++;
	}
	
}
