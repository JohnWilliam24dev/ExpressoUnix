import axios from 'axios';
import * as cheerio from 'cheerio';

export interface RouteInfo {
  distance: string;
  time: string

  
}

export async function travelInfo(origin:string, destination:string):Promise<RouteInfo> {
    //const str_origin =origin.replace("-","%20")
    //const str_destination= destination.replace("-","%20")
    const url =`https://www.distanciasentrecidades.com/pesquisa?from=${origin}%2C%20Brazil&to=${destination}%2C%20Brazil`;
    const { data: html } = await axios.get(url);
    const search = cheerio.load(html);
    const distance= search("#kmsruta").text()
    const time= search("strong").eq(4).text()
    return {distance, time}
}
