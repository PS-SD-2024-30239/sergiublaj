package ro.ps.chefmgmtbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.chefmgmtbackend.dto.user.UserResponseDTO;
import ro.ps.chefmgmtbackend.model.UserEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDTO userEntityToUserResponseDTO(UserEntity userEntity);
}
