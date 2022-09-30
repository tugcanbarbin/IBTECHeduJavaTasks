package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import App.Main;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.hibernate.dao.CommandExecuter;
import net.javaguides.hibernate.dao.CommandExecuterWBag;
import net.javaguides.hibernate.model.*;

public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String type = req.getMethod();

		System.out.println(type);

		if (type.equals("GET")) {
			doGet(req, res);
		} else if (type.equals("POST")) {
			doPost(req, res);
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter().append("Served : ").append(req.getContextPath());
		out.println("res: ");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		CommandExecuterWBag ce = new CommandExecuterWBag();
		Command c = new Command();
		Bag bag = new Bag();

		res.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = res.getWriter();

		try {
			db = dbf.newDocumentBuilder();
			String body = req.getReader().lines().collect(Collectors.joining());

			Document doc = (Document) db.parse(new InputSource(new StringReader(body)));
			doc.getDocumentElement().normalize();

			c = createCommand(doc);
			System.out.println(c.toString());
			bag = createBag(doc);
			System.out.println(bag.toString());

			Bag custBag = new Bag(); custBag.put("id", 14);
			custBag.put("name","bagInput"); custBag.put("surname", "example");
			custBag.put("bdate", "1.1.1.1"); custBag.put("bcountry", "ist");
			Object outbag = CommandExecuterWBag.ExecuteCommandWBag(c, custBag);

			System.out.println("asdasdasdsad");

			// writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			// writer.append("<output>");
			// writer.append((String)
			// outbag.getClass().getDeclaredMethod("toXML").invoke(outbag));
			// writer.append("</output>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private Command createCommand(Document doc) {
		Command c = new Command();

		c.setAlias(doc.getElementsByTagName("commandName").item(0).getTextContent());
		c.setId(Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent()));
		c.setMethodName(doc.getElementsByTagName("methodName").item(0).getTextContent());
		c.setPackageName(doc.getElementsByTagName("packageName").item(0).getTextContent());
		c.setParameterType(doc.getElementsByTagName("parameterType").item(0).getTextContent());

		return c;
	}

	private Bag createBag(Document doc) {
		Bag bag = new Bag();

		NodeList nList = doc.getElementsByTagName("field");
		System.out.println("nlist size : " + nList.getLength());

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				eElement.getAttributes().item(0);
				bag.put(eElement.getAttributes().item(0).getNodeName(),
						eElement.getAttributes().item(0).getTextContent());
			}
		}

		return bag;
	}

}
