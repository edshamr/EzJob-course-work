
const VacancyList = ({ vacancies }) => {
    const handleVacancyClick = (vacancyId) => {
        // Redirect to the detail page for the selected vacancy
        console.log("IN handler");
    };

    return (
        <div>
            {vacancies.map((vacancy) => (
                <div key={vacancy.id}>
                    <h2>{vacancy.title}</h2>
                    <p>{vacancy.description}</p>
                    <button onClick={() => handleVacancyClick(vacancy.id)}>View Details</button>
                </div>
            ))}
        </div>
    );
};

export default VacancyList;