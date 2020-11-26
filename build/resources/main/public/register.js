document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#RegisterForm');
    createEntryForm.addEventListener('submit', (e) => register(e));
});

const register = (e) => {
    fetch(`${URL}/user/sign-up`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem("Token")
        },
        body: JSON.stringify(register)
    }).then((result) => {
        console.log(result)
        if (result.status == 200) {
            toggleRegiser();
            login(username, password);
            resolve(true);
            localStorage.setItem("Token", result.headers.get("Authorization"));
            window.location.replace(`${URL}/homepage.html`);

        }
    })
}