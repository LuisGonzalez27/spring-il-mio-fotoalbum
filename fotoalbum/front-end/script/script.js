//GLOBAL VARIABLES
const PHOTO_URL = 'http://localhost:8080/api/v1/photos';
const contentElement = document.getElementById("content");

//API
const getPhoto = async() => {
    const response = await fetch(PHOTO_URL);
    console.log(response);
    return response;
};

//DOM
const createPhotoItem = (item) => {
    return `<div class="col-4"> 
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">${item.title}</h5>
                        <p class="card-text">${item.description}</p>
                    </div>
                </div>
    </div>`;
};

const createPhotoList = (data) => {
    console.log(data);

    let list = `<div class="row gy-4">`;
    
    data.forEach((element) => {
        list += createPhotoItem(element);
    });

    list += '</div>';
    return list;
};

const loadData = async() => {
    const response = await getPhoto();
    //console.log(response);

    if(response.ok){
        //get data
        const data = await response.json();
        //data filtered for visibility in the front end
        const visibleFilter = data.filter((element) => element.visible === true);
        //render html
        contentElement.innerHTML = createPhotoList(visibleFilter);

    } else {
        console.log('ERROR');
    }
};

//Global code
loadData();
