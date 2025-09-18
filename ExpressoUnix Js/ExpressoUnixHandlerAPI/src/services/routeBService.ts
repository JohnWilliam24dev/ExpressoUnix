import axios from 'axios';
import * as cheerio from 'cheerio';

export interface RouteInfo {
  distance: string;
  time: string

  
}

export async function travelInfoB(origin:string, destination:string):Promise<RouteInfo> {
    //const str_origin =origin.replace("-","%20")
    //const str_destination= destination.replace("-","%20")
    const url =`https://br.distanciacidades.net/calcular?from=${origin}%2CBrazil&to=${destination}%2C%20Brazil`;
    const { data: html } = await axios.get(url);
    const search = cheerio.load(html);
    const distance= search("#drivingbox strong").text()
    const time= search("#timebox strong").text()
    return {distance, time}
}
