package me.jershdervis.monitorj.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import me.jershdervis.monitorj.eventapi.EventManager;
import me.jershdervis.monitorj.eventapi.EventTarget;
import me.jershdervis.monitorj.eventapi.events.EventUILoaded;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Josh on 20/07/2015.
 */
public class FileManager {

    private final File settingsFile = new File("settings.json");
    private final JSONObject socketsDocument;

    public FileManager() {
        EventManager.register(this);

        JSONArray socketsElement = new JSONArray("sockets");
        JSONObject jobj = new JSONObject();
        this.socketsDocument = socketsElement.addObject();
    }

    /**
     * Adds the formatted socket value to the XML file
     *
     * @param name
     * @param port
     * @param desc
     */
    public void saveSocketValue(String name, int port, String desc) throws IOException {
//        Element newSocket = new Element("socket");
        JSONObject jsonObject = new JSONObject();
//        newSocket.addContent(new Element("name").setText(name));
        jsonObject.put("name", name);
//        newSocket.addContent(new Element("port").setText(String.valueOf(port)));
        jsonObject.put("port", port);
//        newSocket.addContent(new Element("desc").setText(desc));
        jsonObject.put("desc", desc);
//        this.socketsDocument.getRootElement().addContent(newSocket);

        this.saveXmlFile(socketsDocument, settingsFile);
    }

    /**
     * Removes the Socket element by port
     * TODO: FIX
     * @param port
     */
    public void removeSocketValue(int port) {
        System.out.println("你妈了个逼removeSocketValue是炸的");
    }

    void saveXmlFile(JSONObject doc, File xmlFile) throws IOException {
        FileWriter fileWriter = new FileWriter(xmlFile);
        JSONWriter jsonWriter = JSONWriter.of();
        jsonWriter.write(doc);
        fileWriter.write(jsonWriter.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Loads Server Socket Listeners when program is initialized
     *
     * @param event
     */
    @EventTarget
    public void onUILoaded(EventUILoaded event) {
        System.out.println("你妈了个逼onUILoaded被我搞炸了");
    }

}
