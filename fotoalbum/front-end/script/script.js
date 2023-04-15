//GLOBAL VARIABLES
const PHOTO_URL = 'http://localhost:8080/api/v1/photos';
const contentElement = document.getElementById("content");
const formSearch = document.getElementById("form-search")

const CONTACT_URL = 'http://localhost:8080/api/v1/contacts/create';
const contactForm = document.getElementById('contact-form');

//API
const getPhoto = async() => {
    const response = await fetch(PHOTO_URL);
    console.log(response);
    return response;
};


//DOM
const createPhotoItem = (item) => {
    return `
    <div class="col-4"> 
                <div class="card h-100">
                    <img src="${item.url}" class="card-img-top" alt="${item.title}">
                    <div class="card-body">
                        <h5 class="card-title">${item.title}</h5>
                        <p class="card-text">${item.description}</p>
                    </div>
                </div>
    </div>`;
};

const createPhotoList = (data) => {
    console.log(data);

    let list = ` <h1>Photo album</h1>
    <div class="row gy-4">`;
    
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
        if (contentElement) {
            contentElement.innerHTML = createPhotoList(visibleFilter);
        }

    } else {
        console.log('ERROR');
    }
};

function filterByTitle(photo){
    const inputSearch = document.getElementById('search-title');
    const inputSearchValue = inputSearch.value;

    if (inputSearchValue) {
        const filterPhoto = photo.filter((element) =>
        element.title.toLowerCase().includes(inputSearchValue.toLowerCase())
      );
      console.log(filterPhoto );
      return filterPhoto.length > 0 ? filterPhoto : null;

    } else {
        loadData();
    }
}

formSearch.addEventListener("submit", async (event) => {
    event.preventDefault();

    const allPhoto = await getPhoto();
    const allPhotoJson = await allPhoto.json();
    const filterList = filterByTitle(allPhotoJson);
    //render html
    if (filterList) {
        contentElement.innerHTML = createPhotoList(filterList);
    } else {
        contentElement.innerHTML = `<h1>Sorry, there are no results available.</h1>`;
    }
});

//Methods to send the contact form
const postContact = async (jsonData) => {
    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
    },
    body: jsonData,  
      
    };
    const response = await fetch(CONTACT_URL, fetchOptions);
    return response;
};

const saveContact = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const formDataObj = Object.fromEntries(formData.entries());
    
    const formDataJson = JSON.stringify(formDataObj);

    const response = await postContact(formDataJson);
    
    const responseBody = await response.json();

    if (response.ok) {
      loadData();
      event.target.reset();
    } else {
      console.log('ERROR');
      console.log(response.status);
      console.log(responseBody);
    }
};

//Global code

loadData();
contactForm.addEventListener("submit", saveContact);
