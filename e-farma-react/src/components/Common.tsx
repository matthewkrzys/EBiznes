export class Common {
    public static readonly URL = "http://localhost:8082";
    public static readonly ID = document.cookie.split(";").filter((value, index) => value.includes("Id")).map(value => value.split("=")[1]).toString();
    public static readonly csrf = document.cookie.split(";").filter((value, index) => value.includes("csrfToken")).map(value => value.split("=")[1]).toString()
    public static readonly authorization = document.cookie.split(";").filter((value, index) => value.includes("Authorization")).map(value => value.split("=")[1]).toString();
}