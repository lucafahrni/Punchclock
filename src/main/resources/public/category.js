const URL = "http://localhost:8081/category.html"

let category =[]

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createCategoryForm');
    createEntryForm.addEventListener('submit', (e) => updateCategory(e));
});

const updateCategory = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    category['category'] = formData.get('category');

    fetch(`${URL}/categories`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem("Token")
        },
        body: JSON.stringify({category})
    }).then((result) => {
        console.log(result)
        if (result.status == 200){
            localStorage.setItem("Token", result.headers.get("Authorization"));
            window.location.replace(`${URL}/homepage.html`);

        }

    });
}
