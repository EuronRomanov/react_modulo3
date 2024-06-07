export const getAllPostsService=()=>{
    fetch('https://jsonplaceholder.typicode.com/posts')
    .then((response)=>{return response.json()})
    .then((json)=>{console.log(json)});
}

export const createPostService=(post,fnExito)=>{
    const config={
        method:'POST',
        body:JSON.stringify({
            title:post.title,
            body:post.body,
            userId:1
        }),
        headers:{
            'Content-type':'application/json'
        }
    };
    fetch('https://jsonplaceholder.typicode.com/posts',config)
    .then((response)=>{return response.json()})
    .then((json)=>{console.log(json); fnExito();});
}
export const updatePostService=()=>{
    const config={
        method:'PUT',
        body:JSON.stringify({
            id:1,
            title:'mensaje final',
            body:'hasta la vista baby',
            userId:1
        }),
        headers:{
            'Content-type':'application/json'
        }
    };
    fetch('https://jsonplaceholder.typicode.com/posts/1',config)
    .then((response)=>{return response.json()})
    .then((json)=>{console.log(json)});
}

export const getByUserIdService=()=>{
    fetch('https://jsonplaceholder.typicode.com/posts?userId=1')
    .then((response)=>{return response.json()})
    .then((json)=>{console.log(json)});
}

export const getAllStoresService=()=>{
    fetch('https://fakestoreapi.com/products')
            .then(res=>res.json())
            .then(json=>console.log(json))
}

export const createProdductoFakeService=()=>{
    const config={
        method:'POST',
        body:JSON.stringify({
            title: 'televisor',
                    price: 13.5,
                    description: 'lorem ipsum set',
                    image: 'https://i.pravatar.cc',
                    category: 'electronic'
        }),
        headers:{
            'Content-type':'application/json'
        }
    };
    fetch('https://fakestoreapi.com/products',config)
    .then((response)=>{return response.json()})
    .then((json)=>{console.log(json)});
}

export const updateProductoFakeService=()=>{
    const config={
        method:'PUT',
        body:JSON.stringify({
            id:21,
            title: 'televisor',
                    price: 1300.5,
                    description: 'oled 75pulgadas',
                    image: 'https://i.pravatar.cc',
                    category: 'electronic'
        }),
        headers:{
            'Content-type':'application/json'
        }
    };
    fetch('https://fakestoreapi.com/products/21',config)
    .then((response)=>{return response.json()})
    .then((json)=>{console.log(json)});
}
export const getDocumentTypes=()=>{
    fetch('http://inventarios.jelastic.saveincloud.net/inventarios-1.0.0/rest/tiposdocumento/recuperar')
    .then((response)=>{return response.json()})
    .then((json)=>{console.log(json)});
}
export const createTypeDocumentService=(documentType,fnExito)=>{
    const config={
        method:'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(documentType)
    };
    fetch('http://inventarios.jelastic.saveincloud.net/inventarios-1.0.0/rest/tiposdocumento/crear',config)
    .then((response)=>{return response.text()})
    .then((json)=>{console.log(json); fnExito();})
    .catch(error => console.log(error));
}