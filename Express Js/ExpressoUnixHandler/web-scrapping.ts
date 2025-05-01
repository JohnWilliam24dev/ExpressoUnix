


const url =`https://www.distanciasentrecidades.com/pesquisa?from=Salvador%2C%20Brazil&to=Jequie%2C%20Brazil`;
console.log(typeof url)
async function getBody(url:string) {
    const request1 = await fetch(url);
    const data =  request1.text();
    return data
}

async function main() {
    console.log(typeof url);
    const result = await getBody(url);
    console.log(result);
}

main();