import styles from '../../styles/UserProfile.module.css'
import {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";

const initialState = {
    name: '',
    email: '',
    stuffCount: 0,
    country: '',
    description: '',
    additionalInfo: ''
};

function CompanyProfile() {
    const [profileData, setProfileData] = useState(initialState);

    useEffect(() => {
        const companyId = localStorage.getItem("companyId")
        axios.get("/api/company/profile", {
            params: {
                company: companyId
            }
        })
            .then(response => {
                setProfileData((prevProfileData) => ({
                    ...prevProfileData,
                    ...response.data
                }));
            })
            .catch(error => {
                console.log(error.data.description)
            });
    }, [])

    return (
        <>
            <div className={styles.layout_a}>
                <Link to="/company" className={styles.apply_button}>Редагувати</Link>
            </div>
            <div className={styles.container}>
                <div className={styles.card}>
                    <div className={styles.card_header}>
                        <div className={styles.avatar}>
                            <img className={styles.img_profile}
                                 src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBUVFRgSFRUYFRYYFRgYEhgSFRgREhISGBUZGRgYGBgcIS4lHB4rIRgYJjgmKy8xNTU1GiQ7QDs0Py40NTEBDAwMEA8QHhISHjQhJCE0NDQxNDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0ND80NDQ0NDQxMf/AABEIAOEA4QMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAEAAIDBQYBBwj/xAA7EAABAwMCAwYEBAUCBwAAAAABAAIRAwQhBTESQVEGImFxgZETMqGxFELB8BYjUmLRFeEHMzRykqLx/8QAGQEAAwEBAQAAAAAAAAAAAAAAAQIDAAQF/8QAIhEAAwEAAgIDAQADAAAAAAAAAAECESExEkEDIlETBDJx/9oADAMBAAIRAxEAPwDz1zk0JFJoQMT0ynFyjaukpANCJT2BRgKakFmBknAmOU4Ca9inoujGFG27EM2mUVSMBLT/AAV8h1KkCEHcUBMDKmoB7zDcTt4rSaPosOBeJ6ztPIIymh5j2zM2HZipVzBgnotTadh2gDiIB58yVrbSiG7NjyVg1mc7quP2UxGdtuyNJoAIko3+GaOO4FdNCllHEbWZW57J0XY4OEnmOipdQ7BgjuHynC9AftKZxjzWxG08Y1DszWpbtPsd1RXDC0wQQfFfQLmse2HAHrKzet9j6dUEtEGP3C2NAxHjKmplWGs6FUt3EFpLRzhVjVm9AwgKe23QgeiKD4KVgw0+nUJglaK2aA1ZuwucBXNG67q56fIFhZ2zgSr6xcAsUzUOEqytdcYOaMVg8tGwkLqy/wDEDOqSp5jajya5ty10JMoo7VY4zHVRs2TeTxE94A3NhNlSXJyoAU66CSBSsQ4ciKSWhWE0wp2sUdMIy2oue4NaM/bxUW9JtjWWxeYaJ/RFW1gXHgAk8z/ur6hZhjeBgkn5jtKutL0wCDEdU8znZWZwF0rRg0THSRu0xzPitHbUABtEKSmwNED6KYKiRTDtIe6k4hKY1dATI2CJ3+ika/8AwmcKa1kImwdUwMKJwgJ5JlJzkGzYcpMGOiKGyGpmFIKi2mwB1XSmVmlpC8p7R9mX0S57RLN/JeyB6D1G0bUbBE/vmg8A0eA81PSaVoe03Z51F5e0dwnYD5SqyhQS1WE2x1tVLVotNYXhUPwoIWu0Sl3f30UL5FXLArqywYVGQ4Ohbi6o90rKm3JeUZeIfAXPVJWn4E9EkfJBMfdVZdK7TqYQjnSiaAVmuDPobUyoi1HGkoalNZMGg7d0ZRQnNT03oUtBRYNWh0tjWM4j87/o1Z21fLh05q1oVeJ8+g6AJZn2xZnk1umM4iFowwNAzsqLRDgFXD34TadCRK2opA9CBxgKRtRFMbApjlICgxUU7HopmwnlJMa5dlHRR0JOC4CulyDZiNKV0lNcgMmdDgmueo3FRmohocI9QtG1GEOEyMrEVdI4HlsY5eS3jH5VXqVIcUpbWzpG5Mbc2cK20p3C1dv2iFV07rhkKC1k5WMvateRCDt7cF8qodqOVaadcTlLaaRRFt8AJJfiAkpaNweQFGWzUI4QUTbvXpV0I+iwdsg65Ur6uEDUqIJCJHCnsUHGnNemaC0WFF8SUVY3EGFVuqd31U9s+D6j2QzgaUek6RUAa3rGVeMdIWU0asHAEbD3WhoPUVR0YGtKaZTaT1MWymMRAlTNKjNNSUxlZI2hAcntcmBOTgHhdLk2VyVjYIuTHOTio3BZmIXvUZKc7dMckGw616F1HMeJUrsIe6fJb5j7oJiUgZ+mFwyqy60MmYC31OmOEY5Jj7YdEyjBMPKKmkPa7YqzsrYs5LaXNo3eFS3bA1yj8m4bACCkpeIJKGGw8uq7prHwlXKhlekhAh9RQPcuFyYSjhsOynByjXQthggOU9FCsKMoNlLXQ0m00N/C0eS0lq8LO6ZQho8lc24hcm8nVnBcUjlGNVfbopj1aWIwghdauNclxQU4CdpTiFA1ymJlYBxILnCksYdCa4JPco3PWCQ1N1x4wuk8015SGBK7lWVq0PaP7h91ZXCpK7pqMH9wU32F9G9pO7o8lHUrAc0H+JPCPJAXNycq7okT3961uZWM1XWW8Rgpuu3js5WLua5J3U/F0+QNmg/1fxXVmPi+KSP8UDSOsxDI+4CBdurLoVDCuJ5CYQiE4kuwtd2U7P0qrfiVieGYa0GJ8yEKpSuQzLp4jLU1Z6YJe0eKvu0nZhlL+ZQks5iS6PKVVdnrcuqtHQyUlUmuBlLl8m4o0YAU7ICkYzEIK8rFmB+/Rc2F0WdO7a0ZIHmoq3aCmB3TMbwFmHte8uO4PKY4R0UttZOIjhP/AKj9hVmkkZrS9Z2lZE7jnAOAuHtdSmDv5Y/+qrfojnt4Q8Mxz6qor9kauSKjT5SZ9E6pCNP0be27UUXnh4oJ2kgK3pXTTsQfVeVfw3UaJLvbGFbWRqMaGB220wErtemPMN9nozKkp7yqTRqjgO8/iO/SPCEfeVCGyAsq40DnHhI+puoKt6xg7zgPMrJa5rNQDhYHAnoFjrupXfJIc70WlpgpYerU9WokwHiU9l2wmA4e68dp0q8yGPnyKurN9Rx7zS0gDf8AcpmkKtPRroCJVM1v81vmVVDUatMta5xc0kCDnh9TlXNuwl4fyAP1UmuQ08Rc8eELcPwuGohq9RNpMzOvP3WOr7rVa0/dZiqMqkAaB+FdT+FJUBg64QkKwu2oNoSoAm05S+F4KwtqYKndbBK7xiuipZRlegaNbxQZHRZZlALZ6A7ipgf0mFH5a8lhf/GtKznxTBY7blKrNGtOGs93Ll6q41CmGtLlHpzSGAkQTn0U5bWo6PlxvSyYhrqiTlF0wieCQmzREzG3178OQ0S88tgPNZ83dxUJHE6M/KeEDyC3l3pzXSYyq5lhwukt8jC00p7QzXl7MlbabVeQXl7ROS5j3+xarrT9MLW1HOe8w+KYex/8xsZxEjOPRaOhTHj6EgIl9MxgH3/yq/0TQn88fZnrG4e13AeJvFIDKklr4/ocdj4FTulriHbYOeUq4t7Uz3s8zOQPIIe5pcbnYwBHsVGi89l1o9NpAIVhd4b6Kt0RnCIVpfjuHyVJX1I1/sYu/d3nPjAJjltv5LNXeqOGWnnADY4Z8XHdbVto17HscJBJkHnCp3WrGuywROO6I9QlnPZR6+EUTNduWML2xDTB4qWBIx3kf/rFwwMfWYyHtDqZjha8dJBw7PNW79KovbHA2DvBc0ewKD1XTuNoa48UYbueERy6bKjqcJqK3dB6tQV2tqMHCeLvAHIzlayzuWsYGEfN/hUGhaa5ggmQNsZV8aAwegKl5fgWpb5Iqj0HXqIl7UJWpoaQwzuqZKoqjcrR39FUFyyFeGCiGEkziK6qi6cuHyheJEVWqANQQiYZbVoRBuUAxieQkcoVrQv8Qr/sxf8Af+GThw+qykIvTahZUY7o4fdK5QY+taelalSloxjn9ELVqDiAHT0hXVuWlgG4I+hWcuxwvgTACSkl0dnlpZUXclYsOFS21THsrChUSyw4FGlP75p34Xwldomf1RtN04VFKZtaAPw4bySNIu8laGnjZN4EfEHkVtZvC1VtqziJ6K01HDeiq7YwZg591Nz9ikv6lxYsEhGXbe6hrDIlHVh3VZLgk3yUFuIcQeZXK9lkkCQeRynuEPVrTYoqfQ7rOSgNoeQIXDYc3LRfCCHuQi4MqKtjYxCc92IXahhR1H7JXwidETmqCqFK96GqvSCorrtkrOahTWiuHqiv3Kvx9mop/hpJ6S6RMR2oxRimjH01G1iiqIaMYxP+GpWsUhZhDyFbAzTTWmCpKyFc4pktGWnp+gasx9FokSBDgdwga9QOe8jr9FhrRzxkEjyWm0y4kAOyYzO4S0mdMWniLSg7PgrGg/2lVTnwef8AsjaD588KJ0IuLd/Lkj6J91TUH5VpRqeKpNAaDQ7C652FA16bVqcgn0TCp1u5y1v9wnxCkovZAPgh9boEgObkgg+ayWs39ThApvcxwORGT7hIm1RZJOeD0mxe0iGlGuIiF5l2Z1isw8NQ8UjDoifA+K0d12h4WkxJ5Acym/pnDEfxt9B924SYwQ4HrsQj6D5bKxejXVSq88TwZMkAQGjpPMrU038Jjl+qVVyGpzgsC7CFuHYUhfhCV34TNiJAVcoG5qQY8AiXuyqu6JLnHxP3UqfAtnH3CGqXCTmFR1KJSoRIHrVZVVdndH1WFBXNNXjAsroSUvwl1W1CclnVoKL8OrN7FGWBcaoiV/woTXNRrqUqSnZzyR8gJFV+FLip2aZG6u6dsGptY9EfNlEUj7eDACJtX8JHsfVFstS47Lup2RYxr/FHz9DyuQmqJE/vHRE2DxEdd/eEHZV2ubHhkeB6qdlWB4jfoeaU6UW1Hpz/AER9FyqKFXOTBJgglWds/HTOOc7FYzLAOUTn80Nc3PC2OfLxQlSuSB1JBg5gDJ9VRADrqqI25TnYBZa8ohzySO6MjoZ6K4c4FgJ5bjq3p9ShHyX8AEgmc8h098ovAptdETKbWQTEYHuSB44hK7oTIIEAYAE56n6ox1uZYS2R+bHLoeqdwENlwgTgRyBmJ57BLiDrBtHe1gmPzR+qvnVAcbEdVQsGwHNxJ6bSPpARnxSPAbg+vVBIzotmHqobh2Ey3qkjPmD1HVQ31aB6+yDYpBUcJAQj2LoqcTvX6KRySmJXYOWKN7AiHIao9KYEqsQNZisHuQtZVkOFf8NdU3Ckn0GGgfYIcacZiFrRaSpmWbQufwoj4mbt9IjJT6tqGjC0NRgQFW2LlnLQVKKB9OU6lZlyv6WlcygO0uoMtKciC84YP1PgjMVTxGxIHrVKVu3iqODeg5nyCy2tdrW1B8NjIZxDvO+bB5dFmdRvn1HF73Ek/QeCAldk/CkueQa2a/TboDnzz4efgrhlQcjsQTzEHnCx7XcBE7FoKura7mM4/TYz4qdR7LTRZ039/J3I4ZxA5nPM/oVpNPf3IGwmPUysgypJHIieHeI3EfvmrnT7ksZAOcCTtn7pKQ5YvMuj82RvwxCVZ7WCXyTETBMDnsnW7uN07dMZ6GVYNtm+aCN/0ylbtFSB4eF5I2hrhPuFIztNHy0CepcFpqlix2XNB8wo3aZSGzB6YTpIeXPspKPbPMPpY5cLTK7U7WOdhtuS3xaZ+sK0daUfzMCTbOkT8oHOB0R1D/Qoma8CYFu/PJoBCsDVc4TwObMSDG0+CtW2jBsI8ky4pjpPlupt/hOsfRHYPHD48xsJ8FW39aSR0nJ26hGXFdoaYwR9+qoLitMuOcyB6ckF2KGWElxb/TufEo16j063LWSd3Z9OQKe9JXYjZE9yEeUQ9DPaskFED3oSq9T1WoOqwqkozGfESTOApKgD1tjgmPqqoF/KJtpeVN3+CYFNaXI6hbAbpW1KAi2hUmd7FZG8BoJ6Lw7tdqnx7l7p7rTws6QF6z2v1H4Fs985LYb5nZeEufJJKvEpciN6QVlymwuIaMkmApHMLiABJmAtZpWhfCZ8R4l5GOjRCaqwaZ0BuLSWDq3CDtnlpAPL6rQ27ARCCvrAbecEdPFc6r0y3j7CaVUugQBz32/wrWlTDB1GPcdFlaVQtgO3B/8AIK1raoIbG4jwG+ySpb6HVI0lndtA+WNh55Vwx8AQsbSuw4me7A5ZIP7lWlHUCGtb+bfJG3il8WHTRl8jCiq1SBv5dF2wqh4HIx6Ix1uHDPonUsGozVy93FIB6ZwMjf6Iu2ds44JmfHKsKtBuGnBlvImd1FToRJAkTI8Aen0WcsbUJrzsg9QvAwZ/x5Ii6uWs36eyzGrXbXuAnESSDg8x9P1SY9A2gijX42GZ+YkyI3XNNsC90n5G5jqeiBOoMZ3C7BIkASRAz5rS6fqNuWhjHARyO63hWakTq0uAl7ELUYjiJ2Ub2JMF0r3U1E+irE00000UMVL6CidazyVw6kmGmmRio/CJK3+EOiSJiewtDuVoLWmAo6FKEW0AboTAjYSwp7qgAkmFRanr9KgDLxPTcrzrWu2FWqS1ri1vKCV0xDYlV+B3/EjXBUcKLDIbl0dVgJUteoXGSZJUKrmChmm1gyoxx24hPkvRrpssEbELy2VvOzOpipS+G499ggf3N5FT+Reykv0ctmfdWHwA4QVC+jDkfbswuSi6KC/0mc8vI5PLPVUde1e0HBx5zC9C4MQYPmgrmwDhtlGbaA5TMLQui10neOf6q9s64PBwulxB4sAmCNgleaE1w6OkmYz5KoqWz6Dg4iW/lc3afHmFVVNC5SN5peoAkARDYbuTmMq+t63M9B9V5rYanwkHijOYk4nwWntNaBG8nzjYCd1nqDwzQVa7eRBOf39vdMqVHASYiPOJWf8A9Xbxl2MAcPXiO/0hduNaaGl3EO7OD12B+6GsIJr19kNaR0mNnH9MfVZPULuHFzBt0jhE4/yinuqXD3FgkOOXRDWDz5+iu62kMbZPptHey/iOSXBFNJ4I9fJiG13F3ETlHNrkQQfZVbCiqbsLrlnPSNLp3aJ7IBPEPFaS17QU37mCvN+JPbXI2KWvimvRk2uj1elXY7YgqUsXl9tqr2bOPuruz7VOB72Qo1/jv0Or/TZuYmFiBs9bpvG8FWHGDsZXPSqXjKy0xnCkupIawhF9rdOiCXOErE6z20e+Ws7o681mLu8e8kuJKFK7phSc7bZLcXT3mXOJnqoiUwuSLk+gw45NXZXCUAiRFjdupPFRu4PuOYKHXFjHp9hcMrMFRnP3B6FHMYvO+z2sGg+Ce4494dPFei0qzXtDmkEHIhcnyTjLzWkrWrj2c11rlNKlg+gL6YO4Q7rbyKsXU1CWQlwOlFc6DScZ4eEnctJb9kO/s+PyVHjpsYHstE4JNYmVUvYMRl3aC6ZNVx25CSVLR0Nk98uf14jjrt6rROZ4LjGZW86ZsQI2kGgBoAH2Urz/AC3D+0/ZS1QoLwRTcf7SjK5M2eXvEOPmfup6RUNb5j5p1Ny7pZy0SSuFPeExyoBCDl0OTJSCGhCaVwWndXum685hAcZCzac16zSrsxvv4gaurCfFPVJJ/KQ6yJNcuJJmAaVzkkkgY4uJJLGEmpJLGOtXo3Zb/p2eX6pJKPzdDx2XjFK1JJc5Y6VHU5pJJQkYXQkkszI45MakkgEZU3Ueof8AKd/2lJJUnsVnllf5j5lcppJLsk56CKiaUklQVDCk1JJKEcUguJIow5JJJYx//9k="
                                 alt="User Avatar"/>
                        </div>
                        <div className={styles.card_title}>
                            <h1 className={styles.user_name}>{profileData.name}</h1>
                        </div>
                    </div>
                    <div className={styles.card_body}>
                        <ul className={styles.ul_body}>
                            <li className={styles.user_info}>
                                <span className={styles.user_span}>Country:</span>
                                {profileData.country}
                            </li>
                            <li className={styles.user_info}>
                                <span className={styles.user_span}>Stuff count:</span>
                                {profileData.stuffCount}
                            </li>
                            <li className={styles.user_info}>
                                <div>
                                    <span className={styles.user_span}>Description:</span>
                                </div>
                                {profileData.description}
                            </li>
                            <li className={styles.user_info}>
                                <div>
                                    <span className={styles.user_span}>Additional information:</span>
                                </div>
                                {profileData.additionalInfo}
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </>
    );
}

export {CompanyProfile};