from math import radians, cos, sin, asin, sqrt
from urllib.request import urlopen
import json

def haversine(lon1, lat1, lon2, lat2):
    """
    Calculate the great circle distance between two points 
    on the earth (specified in decimal degrees)
    """
    # convert decimal degrees to radians 
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])

    # haversine formula 
    dlon = lon2 - lon1 
    dlat = lat2 - lat1 
    a = sin(dlat/2)**2 + cos(lat1) * cos(lat2) * sin(dlon/2)**2
    c = 2 * asin(sqrt(a)) 

    # 6367 km is the radius of the Earth
    km = 6367 * c
    #print (km)
    return km

def main():
    
    url_json = 'http://equipo1.azurewebsites.net/ServiceRutas.svc/GetRutas'
    req = urlopen(url_json)
    encoding = req.headers.get_content_charset()
    obj = json.loads(req.read().decode(encoding))
    f = open("datos.txt", "w")
    for i in range(len(obj)-1):
        lon1 = float(obj[i]["latitud"])
        lat1 = float(obj[i]["longitud"])
        lon2 = float(obj[i+1]["latitud"])
        lat2 = float(obj[i+1]["longitud"])
        rutas = obj[i]['nombre']+"->"+obj[i+1]['nombre']+" "+str(haversine(lon1,lat1, lon2, lat2))+"\n"
        f.write(rutas)
    f.close()
    #print(obj)

if __name__ == '__main__':
    main()